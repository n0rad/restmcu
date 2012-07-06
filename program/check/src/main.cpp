#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <restmcu-config.h>

char *definitionError;
char *criticalProblem_p;

const prog_char PIN_DEFINE_TWICE[] PROGMEM = "Pin %d is define twice";
const prog_char PIN_TYPE_INVALID[] PROGMEM = "Invalid type on pin %d";
const prog_char NOTIFY_VAL_OVERFLOW[] PROGMEM = "Notify val overflow on pin %d";
const prog_char PIN_START_INVALID[] PROGMEM = "Invalid start value for pin %d";
const prog_char PIN_MIN_INVALID[] PROGMEM = "Invalid min value for pin %d";
const prog_char PIN_MAX_INVALID[] PROGMEM = "Invalid max value for pin %d";

const int8_t configGetInputPinIdx(uint8_t pinIdToFind) {
	int8_t pinId;
	for (uint8_t i = 0; -1 != (pinId = (int8_t) pinInputDescription[i].pinId);
			i++) {
		if (pinId == pinIdToFind) {
			return i;
		}
	}
	return -1;
}

const int8_t configGetOutputPinIdx(uint8_t pinIdToFind) {
	int8_t pinId;
	for (uint8_t i = 0; -1 != (pinId = (int8_t) pinOutputDescription[i].pinId);
			i++) {
		if (pinId == pinIdToFind) {
			return i;
		}
	}
	return -1;
}

int errors = 0;

void displayError(const prog_char *progmem_s, int pin) {
	if (!errors) {
		printf("\n");
	}
	errors = 1;
	printf("\033[0;31m - ");
	printf(progmem_s, pin);
	printf("\n\033[0m");
}

char *configCheck() {
	uint8_t ip[4];
	ip[0] = boardDescription.ip[0];
	ip[1] = boardDescription.ip[1];
	ip[2] = boardDescription.ip[2];
	ip[3] = boardDescription.ip[3];
	if (ip[0] == 255 || ip[1] == 255 || ip[2] == 255 || ip[3] == 255 // cannot be 255
	|| ip[0] == 0 || ip[3] == 0) { // cannot start or finish with 0
		displayError("Invalid server ip", 0);
	}

	int8_t pinId;
	for (uint8_t i = 0; -1 != (pinId = (int8_t) pinInputDescription[i].pinId);
			i++) {
		const int8_t inpos = configGetInputPinIdx(pinId);
		const int8_t outpos = configGetOutputPinIdx(pinId);
		if (outpos != -1 || inpos != i) {
			displayError(PIN_DEFINE_TWICE, pinId);
		}
		uint8_t type = pinInputDescription[i].type;
		if (!(type == DIGITAL || type == ANALOG)) {
			displayError(PIN_TYPE_INVALID, pinId);
		}
		for (uint8_t j = 0; j < 4; j++) {
			uint8_t cond = pinInputDescription[i].notifies[j].condition;
			if (cond) {
				uint32_t tmp = (pinInputDescription[i].notifies[j].value);
				float value = tmp;
				if (!(cond == OVER_EQ || cond == UNDER_EQ)) {
					displayError("Invalid notify on pin%d", pinId);
				}
				PinInputConversion conversionFunc =
						(PinInputConversion) (pinInputDescription[i].convertValue);
				if (type == DIGITAL) {
					if (value > conversionFunc(1)
							|| value < conversionFunc(0)) {
						displayError(NOTIFY_VAL_OVERFLOW, pinId);
					}
				} else if (value > conversionFunc(1023)
						|| value < conversionFunc(0)) {
					displayError(NOTIFY_VAL_OVERFLOW, pinId);
				}
			}
		}
	}

	for (uint8_t i = 0; -1 != (pinId = (int8_t) pinOutputDescription[i].pinId);
			i++) {
		const int8_t inpos = configGetInputPinIdx(pinId);
		const int8_t outpos = configGetOutputPinIdx(pinId);
		if (inpos != -1 || outpos != i) {
			displayError(PIN_DEFINE_TWICE, pinId);
		}
		uint8_t type = pinOutputDescription[i].type;
		if (!(type == DIGITAL || type == ANALOG)) {
			displayError(PIN_TYPE_INVALID, pinId);
		}

		uint32_t start = (pinOutputDescription[i].startValue);
		uint32_t min = (pinOutputDescription[i].valueMin);
		uint32_t max = (pinOutputDescription[i].valueMax);
		PinOutputConversion conversionFunc =
				(PinOutputConversion) (pinOutputDescription[i].convertValue);
		if (type == DIGITAL) {
			if (!(conversionFunc(start) == 0 || conversionFunc(start) == 1)) {
				displayError(PIN_START_INVALID, pinId);
			}
			if (conversionFunc(min) != 0) {
				displayError(PIN_MIN_INVALID, pinId);
			}
			if (conversionFunc(max) != 1) {
				displayError(PIN_MAX_INVALID, pinId);
			}
		} else {
			if (conversionFunc(start) > 255 || conversionFunc(start) < 0) {
				displayError(PIN_START_INVALID, pinId);
			}
			if (conversionFunc(min) < 0) {
				displayError(PIN_MIN_INVALID, pinId);
			}
			if (conversionFunc(max) > 255) {
				displayError(PIN_MAX_INVALID, pinId);
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
