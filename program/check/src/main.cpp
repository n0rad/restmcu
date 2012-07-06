#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <restmcu-config.h>

char *definitionError;
char *criticalProblem_p;

const prog_char PIN_DEFINE_TWICE[] PROGMEM = "pin%d is define twice";
const prog_char PIN_TYPE_INVALID[] PROGMEM = "invalid type on pin%d";
const prog_char NOTIFY_VAL_OVERFLOW[] PROGMEM = "notify val overflow on pin%d";
const prog_char PIN_START_INVALID[] PROGMEM = "invalid start value for pin%d";
const prog_char PIN_MIN_INVALID[] PROGMEM = "invalid min value for pin%d";
const prog_char PIN_MAX_INVALID[] PROGMEM = "invalid max value for pin%d";

const int8_t configGetInputPinIdx(uint8_t pinIdToFind) {
    int8_t pinId;
    for (uint8_t i = 0; -1 != (pinId = (int8_t) pinInputDescription[i].pinId); i++) {
        if (pinId == pinIdToFind) {
            return i;
        }
    }
    return -1;
}

const int8_t configGetOutputPinIdx(uint8_t pinIdToFind) {
    int8_t pinId;
    for (uint8_t i = 0; -1 != (pinId = (int8_t) pinOutputDescription[i].pinId); i++) {
        if (pinId == pinIdToFind) {
            return i;
        }
    }
    return -1;
}




char *buildGlobalError_P(const prog_char *progmem_s, int pin) {
	char *ptr = (char *) malloc(100 * sizeof(char));
	memset(ptr, 0, 100 * sizeof(char));
	sprintf(ptr, progmem_s, pin);
	return ptr;
}

char *configCheck() {
	uint8_t ip[4];
	ip[0] = boardDescription.ip[0];
	ip[1] = boardDescription.ip[1];
	ip[2] = boardDescription.ip[2];
	ip[3] = boardDescription.ip[3];
	if (ip[0] == 255 || ip[1] == 255 || ip[2] == 255 || ip[3] == 255 // cannot be 255
	|| ip[0] == 0 || ip[3] == 0) { // cannot start or finish with 0
		return buildGlobalError_P("invalid ip", 0);
	}

	int8_t pinId;
	for (uint8_t i = 0; -1 != (pinId =
			(int8_t) pinInputDescription[i].pinId); i++) {
		const int8_t inpos = configGetInputPinIdx(pinId);
		const int8_t outpos = configGetOutputPinIdx(pinId);
		if (outpos != -1 || inpos != i) {
			return buildGlobalError_P(PIN_DEFINE_TWICE, pinId);
		}
		uint8_t type = pinInputDescription[i].type;
		if (!(type == DIGITAL || type == ANALOG)) {
			return buildGlobalError_P(PIN_TYPE_INVALID, pinId);
		}
		for (uint8_t j = 0; j < 4; j++) {
			uint8_t cond =
					pinInputDescription[i].notifies[j].condition;
			if (cond) {
				uint32_t tmp =
						(pinInputDescription[i].notifies[j].value);
				float value = tmp;
				if (!(cond == OVER_EQ || cond == UNDER_EQ)) {
					return buildGlobalError_P("invalid notify on pin%d", pinId);
				}
				PinInputConversion conversionFunc =
						(PinInputConversion) (pinInputDescription[i].convertValue);
				if (type == DIGITAL) {
					if (value > conversionFunc(1)
							|| value < conversionFunc(0)) {
						return buildGlobalError_P(NOTIFY_VAL_OVERFLOW, pinId);
					}
				} else if (value > conversionFunc(1023)
						|| value < conversionFunc(0)) {
					return buildGlobalError_P(NOTIFY_VAL_OVERFLOW, pinId);
				}
			}
		}
	}

	for (uint8_t i = 0; -1 != (pinId =
			(int8_t) pinOutputDescription[i].pinId); i++) {
		const int8_t inpos = configGetInputPinIdx(pinId);
		const int8_t outpos = configGetOutputPinIdx(pinId);
		if (inpos != -1 || outpos != i) {
			return buildGlobalError_P(PIN_DEFINE_TWICE, pinId);
		}
		uint8_t type = pinOutputDescription[i].type;
		if (!(type == DIGITAL || type == ANALOG)) {
			return buildGlobalError_P(PIN_TYPE_INVALID, pinId);
		}

		uint32_t start = (pinOutputDescription[i].startValue);
		uint32_t min = (pinOutputDescription[i].valueMin);
		uint32_t max = (pinOutputDescription[i].valueMax);
		PinOutputConversion conversionFunc =
				(PinOutputConversion) (pinOutputDescription[i].convertValue);
		if (type == DIGITAL) {
			if (!(conversionFunc(start) == 0
					|| conversionFunc(start) == 1)) {
				return buildGlobalError_P(PIN_START_INVALID, pinId);
			}
			if (conversionFunc(min) != 0) {
				return buildGlobalError_P(PIN_MIN_INVALID, pinId);
			}
			if (conversionFunc(max) != 1) {
				return buildGlobalError_P(PIN_MAX_INVALID, pinId);
			}
		} else {
			if (conversionFunc(start) > 255
					|| conversionFunc(start) < 0) {
				return buildGlobalError_P(PIN_START_INVALID, pinId);
			}
			if (conversionFunc(min) < 0) {
				return buildGlobalError_P(PIN_MIN_INVALID, pinId);
			}
			if (conversionFunc(max) > 255) {
				return buildGlobalError_P(PIN_MAX_INVALID, pinId);
			}
		}

	}


	return 0;
}

int main() {

	char * definitionError = configCheck();
	if (definitionError) {
		printf("%s\n", definitionError);
		exit(1);
	}

//	char *error = boardCheckConfig();
//	if (error) {
//		return error;
//	}

//    if (criticalProblem_p) {
//        plen = startResponseHeader(&buf, HEADER_500);
//        plen = appendErrorMsg_P(buf, plen, criticalProblem_p);
//        return plen;
//    }
//    if (definitionError) {
//        plen = startResponseHeader(&buf, HEADER_500);
//        plen = appendErrorMsg(buf, plen, definitionError);
//        return plen;
//    }

}
