#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network.
// gateway and subnet are optional:
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192,168,42, 245);
IPAddress gateway(192, 168, 42, 1);
IPAddress subnet(255, 255, 255, 0);



// Initialize the Ethernet server library
// with the IP address and port you want to use
// (port 80 is default for HTTP):
EthernetServer server(80);

void setup() {
    // start the Ethernet connection and the server:
    Ethernet.begin(mac, ip);
    server.begin();
}

void loop() {
    // listen for incoming clients
     EthernetClient client = server.available();
     if (client) {
       // an http request ends with a blank line
       boolean currentLineIsBlank = true;
       while (client.connected()) {
         if (client.available()) {
           char c = client.read();
           // if you've gotten to the end of the line (received a newline
           // character) and the line is blank, the http request has ended,
           // so you can send a reply
           if (c == '\n' && currentLineIsBlank) {
             // send a standard http response header
             client.println("HTTP/1.1 200 OK");
             client.println("Content-Type: text/html");
             client.println();

             // output the value of each analog input pin
             for (int analogChannel = 0; analogChannel < 6; analogChannel++) {
               client.print("analog input ");
               client.print(analogChannel);
               client.print(" is ");
               client.print(analogRead(analogChannel));
               client.println("<br />");
             }
             break;
           }
           if (c == '\n') {
             // you're starting a new line
             currentLineIsBlank = true;
           }
           else if (c != '\r') {
             // you've gotten a character on the current line
             currentLineIsBlank = false;
           }
         }
       }
       // give the web browser time to receive the data
       delay(1);
       // close the connection:
       client.stop();
     }
}


int main(void) {
    init(); // load init of arduino

    setup();

    while (1) {
        loop();
    }
}
