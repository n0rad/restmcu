#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <restmcu-config.h>

char *definitionError;
char *criticalProblem_p;

const prog_char LINE_DEFINE_TWICE[] PROGMEM = "Line %d is define twice";
const prog_char LINE_TYPE_INVALID[] PROGMEM = "Invalid type on line %d";
const prog_char NOTIFY_VAL_OVERFLOW[] PROGMEM = "Notify val overflow on line %d";
const prog_char LINE_START_INVALID[] PROGMEM = "Invalid start value for line %d";
const prog_char LINE_MIN_INVALID[] PROGMEM = "Invalid min value for line %d";
const prog_char LINE_MAX_INVALID[] PROGMEM = "Invalid max value for line %d";

const int8_t configGetInputLineIdx(uint8_t lineIdToFind) {
	int8_t lineId;
	for (uint8_t i = 0; -1 != (lineId = (int8_t) lineInputDescription[i].lineId);
			i++) {
		if (lineId == lineIdToFind) {
			return i;
		}
	}
	return -1;
}

const int8_t configGetOutputLineIdx(uint8_t lineIdToFind) {
	int8_t lineId;
	for (uint8_t i = 0; -1 != (lineId = (int8_t) lineOutputDescription[i].lineId);
			i++) {
		if (lineId == lineIdToFind) {
			return i;
		}
	}
	return -1;
}

int errors = 0;

void displayError(const prog_char *progmem_s, int line) {
	if (!errors) {
		printf("\n");
	}
	errors = 1;
	printf("\033[0;31m - ");
	printf(progmem_s, line);
	printf("\n\033[0m");
}

char *configCheck() {
	uint8_t ip[4];
	ip[0] = boardSettings.ip[0];
	ip[1] = boardSettings.ip[1];
	ip[2] = boardSettings.ip[2];
	ip[3] = boardSettings.ip[3];
	if (ip[0] == 255 || ip[1] == 255 || ip[2] == 255 || ip[3] == 255 // cannot be 255
	|| ip[0] == 0 || ip[3] == 0) { // cannot start or finish with 0
		displayError("Invalid server ip", 0);
	}

	int8_t lineId;
	for (uint8_t i = 0; -1 != (lineId = (int8_t) lineInputDescription[i].lineId);
			i++) {
		const int8_t inpos = configGetInputLineIdx(lineId);
		const int8_t outpos = configGetOutputLineIdx(lineId);
		if (outpos != -1 || inpos != i) {
			displayError(LINE_DEFINE_TWICE, lineId);
		}
		uint8_t type = lineInputDescription[i].type;
		if (!(type == DIGITAL || type == ANALOG)) {
			displayError(LINE_TYPE_INVALID, lineId);
		}
		for (uint8_t j = 0; j < 4; j++) {
			uint8_t cond = lineInputSettings[i].notifies[j].condition;
			if (cond) {
				uint32_t tmp = (lineInputSettings[i].notifies[j].value);
				float value = tmp;
				if (!(cond == OVER_EQ || cond == UNDER_EQ)) {
					displayError("Invalid notify on line%d", lineId);
				}
				LineInputConversion conversionFunc =
						(LineInputConversion) (lineInputDescription[i].convertValue);
				if (type == DIGITAL) {
					if (value > conversionFunc(1)
							|| value < conversionFunc(0)) {
						displayError(NOTIFY_VAL_OVERFLOW, lineId);
					}
				} else if (value > conversionFunc(1023)
						|| value < conversionFunc(0)) {
					displayError(NOTIFY_VAL_OVERFLOW, lineId);
				}
			}
		}
	}

	for (uint8_t i = 0; -1 != (lineId = (int8_t) lineOutputDescription[i].lineId);
			i++) {
		const int8_t inpos = configGetInputLineIdx(lineId);
		const int8_t outpos = configGetOutputLineIdx(lineId);
		if (inpos != -1 || outpos != i) {
			displayError(LINE_DEFINE_TWICE, lineId);
		}
		uint8_t type = lineOutputDescription[i].type;
		if (!(type == DIGITAL || type == ANALOG)) {
			displayError(LINE_TYPE_INVALID, lineId);
		}

		uint32_t start = (lineOutputSettings[i].lastValue);
		uint32_t min = (lineOutputDescription[i].valueMin);
		uint32_t max = (lineOutputDescription[i].valueMax);
		LineOutputConversion conversionFunc =
				(LineOutputConversion) (lineOutputDescription[i].convertValue);
		if (type == DIGITAL) {
			if (!(conversionFunc(start) == 0 || conversionFunc(start) == 1)) {
				displayError(LINE_START_INVALID, lineId);
			}
			if (conversionFunc(min) != 0) {
				displayError(LINE_MIN_INVALID, lineId);
			}
			if (conversionFunc(max) != 1) {
				displayError(LINE_MAX_INVALID, lineId);
			}
		} else {
			if (conversionFunc(start) > 255 || conversionFunc(start) < 0) {
				displayError(LINE_START_INVALID, lineId);
			}
			if (conversionFunc(min) < 0) {
				displayError(LINE_MIN_INVALID, lineId);
			}
			if (conversionFunc(max) > 255) {
				displayError(LINE_MAX_INVALID, lineId);
			}
		}

	}

	return 0;
}

int main() {
	printf("\033[0;32mChecking configuration...   \033[0m");
	configCheck();
	if (errors) {
		exit(1);
	} else {
		printf("[ \033[0;32mOK\033[0m ]\n");
	}
	return 0;
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
