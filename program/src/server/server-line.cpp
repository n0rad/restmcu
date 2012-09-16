#include "server-line.h"

uint16_t linePutValue(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource) {
    if (webResource->lineIdx < lineInputSize) {
        plen = startResponseHeader(&buf, HEADER_400);
        plen = appendErrorMsg_P(buf, plen, ERROR_MSG_UPDATE, PSTR("Cannot set value on INPUT line"));
        return plen;
    }
    float value = atof(&buf[dat_p]);
    const prog_char *res = setLineValue(webResource->lineIdx - lineInputSize, value);
    if (res) {
        plen = startResponseHeader(&buf, HEADER_400);
        plen = appendErrorMsg_P(buf, plen, ERROR_MSG_UPDATE, res);
    } else {
        plen = startResponseHeader(&buf, HEADER_200);
    }
    return plen;
}

uint16_t lineGetValue(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource) {
    plen = startResponseHeader(&buf, HEADER_200);
    plen = addToBufferTCP(buf, plen, getLineValue(webResource->lineIdx));
    return plen;
}

////////////////////////////////////////////

uint16_t linePutSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource) {
    currentSetLineIdx = webResource->lineIdx;
    const prog_char *error = jsonParse(&buf[dat_p], &linePutSettingsObj);
    if (error) {
        plen = startResponseHeader(&buf, HEADER_400);
        plen = appendErrorMsg_P(buf, plen, ERROR_MSG_UPDATE, error);
    } else {
        plen = startResponseHeader(&buf, HEADER_200);
    }
    return plen;
}

uint16_t lineGetSettings(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource) {
    plen = startResponseHeader(&buf, HEADER_200);

    plen = addToBufferTCP_P(buf, plen, PSTR("{\"name\":\""));
    plen = addToBufferTCP_E(buf, plen, settingsLineGetName_E(webResource->lineIdx));
    plen = addToBufferTCP(buf, plen, '"');

    if (webResource->lineIdx < lineInputSize) {
        plen = addToBufferTCP_P(buf, plen, PSTR(",\"notifies\":["));
        for (uint8_t i = 0; i < LINE_NUMBER_OF_NOTIFY; i++) {
            t_notify *notify = settingsLineGetNotify(webResource->lineIdx, i);
            if (notify->condition > 0) {
                if (i) {
                    plen = addToBufferTCP(buf, plen, ',');
                }
                plen = addToBufferTCP_P(buf, plen, PSTR("{\"notifyCondition\":\""));
                plen = addToBufferTCP_P(buf, plen, lineNotification[notify->condition - 1]);

                plen = addToBufferTCP_P(buf, plen, PSTR("\",\"notifyValue\":"));
                plen = addToBufferTCP(buf, plen, notify->value);
                plen = addToBufferTCP(buf, plen, '}');
            }
        }
        plen = addToBufferTCP(buf, plen, ']');
    }


    plen = addToBufferTCP(buf, plen, '}');
    return plen;
}
uint16_t lineGet(char *buf, uint16_t dat_p, uint16_t plen, t_webRequest *webResource) {
    plen = startResponseHeader(&buf, HEADER_200);

    plen = addToBufferTCP_P(buf, plen, PSTR("{\"direction\":\""));
    plen = addToBufferTCP_P(buf, plen, webResource->lineIdx < lineInputSize ? STR_INPUT : STR_OUTPUT);

    plen = addToBufferTCP_P(buf, plen, JSON_DESCRIPTION);
    plen = addToBufferTCP_P(buf, plen, (const prog_char *) (webResource->lineIdx < lineInputSize ?
            &lineInputDescription[webResource->lineIdx].description : &lineOutputDescription[webResource->lineIdx - lineInputSize].description));


    plen = addToBufferTCP_P(buf, plen, PSTR("\",\"id\":"));
    plen = addToBufferTCP(buf, plen, (uint16_t) pgm_read_byte(webResource->lineIdx < lineInputSize ?
            &lineInputDescription[webResource->lineIdx].lineId : &lineOutputDescription[webResource->lineIdx - lineInputSize].lineId));

    uint8_t type = pgm_read_byte(webResource->lineIdx < lineInputSize ?
            &lineInputDescription[webResource->lineIdx].type : &lineOutputDescription[webResource->lineIdx - lineInputSize].type);
    plen = addToBufferTCP_P(buf, plen, PSTR(",\"type\":\""));
    plen = addToBufferTCP_P(buf, plen, lineType[type - 1]);

    if (webResource->lineIdx < lineInputSize) {
        LineInputConversion conversion = (LineInputConversion) pgm_read_word(&(lineInputDescription[webResource->lineIdx].convertValue));

        plen = addToBufferTCP_P(buf, plen, LINE_MIN);
        plen = addToBufferTCP(buf, plen, conversion(0));

        plen = addToBufferTCP_P(buf, plen, LINE_MAX);
        plen = addToBufferTCP(buf, plen, conversion(type == ANALOG ? 1023 : 1));

        plen = addToBufferTCP_P(buf, plen, PSTR(",\"pullup\":"));
        plen = addToBufferTCP(buf, plen, (uint16_t) pgm_read_byte(&lineInputDescription[webResource->lineIdx].pullup));

    } else {
        float minValue;
        memcpy_P(&minValue, &lineOutputDescription[webResource->lineIdx - lineInputSize].valueMin, sizeof(float));
        plen = addToBufferTCP_P(buf, plen, LINE_MIN);
        plen = addToBufferTCP(buf, plen, minValue);

        float maxValue;
        memcpy_P(&maxValue, &lineOutputDescription[webResource->lineIdx - lineInputSize].valueMax, sizeof(float));
        plen = addToBufferTCP_P(buf, plen, LINE_MAX);
        plen = addToBufferTCP(buf, plen, maxValue);
    }

    plen = addToBufferTCP_P(buf, plen, PSTR(",\"value\":"));
    plen = addToBufferTCP(buf, plen, getLineValue(webResource->lineIdx));
    plen = addToBufferTCP(buf, plen, '}');
    return plen;
}


