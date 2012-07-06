#ifndef __PGMSPACE_H__
#define __PGMSPACE_H__

typedef  char prog_char;

#ifndef __ATTR_PROGMEM__
#define __ATTR_PROGMEM__ __attribute__(())
#endif

#define PROGMEM __ATTR_PROGMEM__

#endif
