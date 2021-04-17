boolean touchAvion()
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

boolean touchShip()
{
  boolean tocadoS = false;
  for(int i = 0; i < touches.length; i++)
  {
    if(touches[i].x >= width-xmenos && touches[i].x <= (width-xmenos+tam) && touches[i].y >= (height*0.8)-tam && touches[i].y <= (height*0.8))
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

void touchStart()
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
