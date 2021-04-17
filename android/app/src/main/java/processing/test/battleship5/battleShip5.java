package processing.test.battleship5;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class battleShip5 extends PApplet {

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


public void setup()
{
  
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

public void draw()
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

public void pantallaUNO()
{
  image(back, 0, 0, width+(width*0.3f), height+(height*0.5f));
  image(title, (width/2)-((width*0.9f)/2), height*0.2f, width*0.9f, height/2);
  
}

public void pantallaDOS()
{
  tam = width*0.1f;
  image(back, 0, 0, width+(width*0.3f), height+(height*0.5f));
  image(ship, width-xmenos, (height*0.8f)-tam, tam, tam);
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
    if(pAY <= (height*0.8f)-tam && pAX >= width-xmenos && pAX <= (width-xmenos+tam))
    {
      dio = -1;
    }
  }
}

public void pantallaTRES()
{
  if(dio == 1)
  {
    image(back, 0, 0, width+(width*0.3f), height+(height*0.5f));
    image(ship, width-xmenos, (height*0.8f)-tam, tam, tam);
    image(explo, xmas-tam, 0, tam, tam);
  }
  else if(dio == -1)
  {
    image(back, 0, 0, width+(width*0.3f), height+(height*0.5f));
    image(explo, width-xmenos, (height*0.8f)-tam, tam, tam);
    image(avion, xmas-tam, 0, tam, tam);
  }
}
public void caidaAM()
{
  float posavion = (tam / 2) + xmas - tam;
  posmisilesAX[totalmisilesA] = posavion;
  posmisilesAY[totalmisilesA] = tam;
  for(int i = 0; i < totalmisilesA; i++)
  {
    posmisilesAY[i] = posmisilesAY[i] + 15;
  }
}
public void caidaSM()
{
  float posship = width - xmenos + (tam / 2);
  posmisilesSX[totalmisilesS] = posship;
  posmisilesSY[totalmisilesS] = ((height * 0.8f) - tam)-(tam/3);
  for(int i = 0; i < totalmisilesS; i++)
  {
    if(posmisilesSY[i] <= tam/2)
    {
      flagMS[i] = -1;
    }
    if(flagMS[i] == 1)
    {
      posmisilesSX[i] = posmisilesSX[i] - 2.5f;
      posmisilesSY[i] = posmisilesSY[i] - 15;
    }
    if(flagMS[i] == -1)
    {
      posmisilesSX[i] = posmisilesSX[i] - 2.5f;
      posmisilesSY[i] = posmisilesSY[i] + 15;
    }
  }
}
public void lines()
{
  line(0,height*0.2f,width,height*0.2f);
  line(0,height*0.4f,width,height*0.4f);
  line(0,height*0.6f,width,height*0.6f);
  line(0,height*0.8f,width,height*0.8f);
}
public void movAvion()
{
  if(xmas <= width+(tam))
  {
    xmas = xmas + 5;
  }
  else
  {
    xmas = 0;
  }
}
public void movShip()
{
  if(width-xmenos >= -tam)
  {
    xmenos = xmenos + 5;
  }
  else
  {
    xmenos = 0;
  }
}
public void recargaA()
{
  totalmisilesA = 0;
  for(int i = 0; i < totalmisilesA; i++)
  {
    posmisilesAX[i] = 0;
    posmisilesAY[i] = 0;
  }
}

public void recargaS()
{
  totalmisilesS = 0;
  for(int i = 0; i < totalmisilesS; i++)
  {
    posmisilesSX[i] = 0;
    posmisilesSY[i] = 0;
    flagMS[i] = 0;
  }
}
public boolean touchAvion()
{
  boolean tocadoA = false;
  for(int i = 0; i < touches.length; i++)
  {
    if(touches[i].x >= xmas-tam && touches[i].x <= xmas && touches[i].y <= tam)
    {
      tocadoA = true;
    }
    else
    {
      tocadoA = false;
    }
  }
  return tocadoA;
}

public boolean touchShip()
{
  boolean tocadoS = false;
  for(int i = 0; i < touches.length; i++)
  {
    if(touches[i].x >= width-xmenos && touches[i].x <= (width-xmenos+tam) && touches[i].y >= (height*0.8f)-tam && touches[i].y <= (height*0.8f))
    {
      tocadoS = true;
      int j = 0;
      do
      {
        if(flagMS[j] == 0)
        {
          flagMS[j] = 1;
        }
        j++;
      }while(j <= totalmisilesS);
    }
    else
    {
      tocadoS = false;
    }
  }
  return tocadoS;
}

public void touchStart()
{
  for(int i = 0; i < touches.length; i++)
  {
    if(touches[i].x >= 0 && touches[i].y >= 0)
    {
      start = true;
    }
    else
    {
      start = false;
    }
  }
}
  public void settings() {  fullScreen(); }
}
