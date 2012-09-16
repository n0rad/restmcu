
void OneWireReset(int Line) // reset.  Should improve to act as a presence pulse
{
     digitalWrite(Line, LOW);
     lineMode(Line, OUTPUT); // bring low for 500 us
     delayMicroseconds(500);
     lineMode(Line, INPUT);
     delayMicroseconds(500);
}


byte OneWireInByte(int Line) // read byte, least sig byte first
{
    byte d, n, b;

    for (n=0; n<8; n++)
    {
        digitalWrite(Line, LOW);
        lineMode(Line, OUTPUT);
        delayMicroseconds(5);
        lineMode(Line, INPUT);
        delayMicroseconds(5);
        b = digitalRead(Line);
        delayMicroseconds(50);
        d = (d >> 1) | (b<<7); // shift d to right and insert b in most sig bit position
    }
    return(d);
}


void OneWireOutByte(int Line, byte d) // output byte d (least sig bit first).
{
   byte n;

   for(n=8; n!=0; n--)
   {
      if ((d & 0x01) == 1)  // test least sig bit
      {
         digitalWrite(Line, LOW);
         lineMode(Line, OUTPUT);
         delayMicroseconds(5);
         lineMode(Line, INPUT);
         delayMicroseconds(60);
      }
      else
      {
         digitalWrite(Line, LOW);
         lineMode(Line, OUTPUT);
         delayMicroseconds(60);
         lineMode(Line, INPUT);
      }

      d=d>>1; // now the next bit is in the least sig bit position.
   }

}



void getCurrentTemp(char *temp)
{
  int HighByte, LowByte, TReading, Tc_100, sign, whole, fract;

  OneWireReset(TEMP_LINE);
  OneWireOutByte(TEMP_LINE, 0xcc);
  OneWireOutByte(TEMP_LINE, 0x44); // perform temperature conversion, strong pullup for one sec

  OneWireReset(TEMP_LINE);
  OneWireOutByte(TEMP_LINE, 0xcc);
  OneWireOutByte(TEMP_LINE, 0xbe);

  LowByte = OneWireInByte(TEMP_LINE);
  HighByte = OneWireInByte(TEMP_LINE);
  TReading = (HighByte << 8) + LowByte;
  sign = TReading & 0x8000;  // test most sig bit
  if (sign) // negative
  {
    TReading = (TReading ^ 0xffff) + 1; // 2's comp
  }
  Tc_100 = (6 * TReading) + TReading / 4;    // multiply by (100 * 0.0625) or 6.25

  whole = Tc_100 / 100;  // separate off the whole and fractional portions
  fract = Tc_100 % 100;

    if(sign) temp[0]='-';
    else          temp[0]='+';

    temp[1]= (whole-(whole/100)*100)/10 +'0' ;
    temp[2]= whole-(whole/10)*10 +'0';

    temp[3]='.';
    temp[4]=fract/10 +'0';
    temp[5]=fract-(fract/10)*10 +'0';

    temp[6] = '\0';
}

