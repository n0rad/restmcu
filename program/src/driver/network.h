#ifndef NETWORK_H
#define NETWORK_H

#include <avr/pgmspace.h>

#include "../restmcu.h"
#include "../server/server.h"
#include "../client/client.h"
#include "../settings/settings.h"

void networkSetup();
void networkManage();

#endif
