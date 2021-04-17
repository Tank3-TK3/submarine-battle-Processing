void caidaAM()
{
  float posavion = (tam / 2) + xmas - tam;
  posmisilesAX[totalmisilesA] = posavion;
  posmisilesAY[totalmisilesA] = tam;
  for(int i = 0; i < totalmisilesA; i++)
  {
    posmisilesAY[i] = posmisilesAY[i] + 15;
  }
}
void caidaSM()
{
  float posship = width - xmenos + (tam / 2);
  posmisilesSX[totalmisilesS] = posship;
  posmisilesSY[totalmisilesS] = ((height * 0.8) - tam)-(tam/3);
  for(int i = 0; i < totalmisilesS; i++)
  {
    if(posmisilesSY[i] <= tam/2)
    {
      flagMS[i] = -1;
    }
    if(flagMS[i] == 1)
    {
      posmisilesSX[i] = posmisilesSX[i] - 2.5;
      posmisilesSY[i] = posmisilesSY[i] - 15;
    }
    if(flagMS[i] == -1)
    {
      posmisilesSX[i] = posmisilesSX[i] - 2.5;
      posmisilesSY[i] = posmisilesSY[i] + 15;
    }
  }
}
