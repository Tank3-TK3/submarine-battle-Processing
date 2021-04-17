PImage back, ship, avion, explo,title;
int xmas = 0;
int xmenos =0;
int totalmisilesA = 0;
int dio = 0;
boolean start = false;
PImage[] misilesA = new PImage[40];
float[] posmisilesAX = new float[40];
float[] posmisilesAY = new float[40];
int totalmisilesS = 0;
PImage[] misilesS = new PImage[40];
float[] posmisilesSX = new float[40];
float[] posmisilesSY = new float[40];
int[] flagMS = new int[40];
float tam;


void setup()
{
  fullScreen();
  orientation(LANDSCAPE);
  title = loadImage("BS.png");
  back = loadImage("mar.jpg");
  ship = loadImage("submarino1.png");
  avion = loadImage("submarino2.png");
  for (int i = 0; i < 40; i++)
  {
    misilesA[i] = loadImage("bomba.png");
    misilesS[i] = loadImage("bomba2.png");
    flagMS[i] = 0;
  }
  explo = loadImage("explosion.png");
}

void draw()
{
  if(start == false)
  {
    pantallaUNO();
    touchStart();
  }
  else if(dio == 0)
  {
    pantallaDOS();
  }
  else
  {
    pantallaTRES();
  }
}

void pantallaUNO()
{
  image(back, 0, 0, width+(width*0.3), height+(height*0.5));
  image(title, (width/2)-((width*0.9)/2), height*0.2, width*0.9, height/2);
  
}

void pantallaDOS()
{
  tam = width*0.1;
  image(back, 0, 0, width+(width*0.3), height+(height*0.5));
  image(ship, width-xmenos, (height*0.8)-tam, tam, tam);
  image(avion, xmas-tam, 0, tam, tam);
  lines();
  movShip();
  movAvion();
  if(touchAvion())
  {
    if(totalmisilesA < 39)
    {
      totalmisilesA++;
    }
    else if(totalmisilesA >= 39)
    {
      if(posmisilesAY[38] > height)
      {
        recargaA();
      }
    }
  }
  if(touchShip())
  {
    if(totalmisilesS < 39)
    {
      totalmisilesS++;
    }
    else if(totalmisilesS >= 39)
    {
      if(posmisilesSY[38] > height)
      {
        recargaS();
      }
    }
  }
  if(totalmisilesA > -1)
  {
    caidaAM();
    for(int i = 0; i < totalmisilesA; i++)
    {
      image(misilesA[i], posmisilesAX[i], posmisilesAY[i], tam/3, tam/3);
    }
  }
  if(totalmisilesS > -1)
  {
    caidaSM();
    for(int i = 0; i < totalmisilesS; i++)
    {
      image(misilesS[i], posmisilesSX[i], posmisilesSY[i], tam/3, tam/3);
    }
  }
  for(int i = 0; i < totalmisilesS; i++)
  {
    float pSX = posmisilesSX[i]+(((posmisilesSX[i]+tam/3)-posmisilesSX[i])/2);
    float pSY = posmisilesSY[i];
    if(pSY <= tam/2 && pSX >= xmas-tam && pSX <= xmas)
    {
      dio = 1;
    }
  }
  for(int i = 0; i < totalmisilesA; i++)
  {
    float pAX = posmisilesAX[i]+(((posmisilesAX[i]+tam/3)-posmisilesAX[i])/2);
    float pAY = posmisilesSY[i];
    if(pAY <= (height*0.8)-tam && pAX >= width-xmenos && pAX <= (width-xmenos+tam))
    {
      dio = -1;
    }
  }
}

void pantallaTRES()
{
  if(dio == 1)
  {
    image(back, 0, 0, width+(width*0.3), height+(height*0.5));
    image(ship, width-xmenos, (height*0.8)-tam, tam, tam);
    image(explo, xmas-tam, 0, tam, tam);
  }
  else if(dio == -1)
  {
    image(back, 0, 0, width+(width*0.3), height+(height*0.5));
    image(explo, width-xmenos, (height*0.8)-tam, tam, tam);
    image(avion, xmas-tam, 0, tam, tam);
  }
}
