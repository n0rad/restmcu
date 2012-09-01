
class Print
{

    virtual size_t write(uint8_t) = 0;

};


class Sha256Class : public Print
{
  public:
    using Print::write;
  private:
};
extern Sha256Class Sha256;
