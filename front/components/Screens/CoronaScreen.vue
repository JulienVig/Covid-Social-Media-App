<template>
  <view class="container">
    <text class="gestes_barrières">Les Gestes Barrières</text>
    <text>Il est important de régulièrement se laver les mains, tousser dans son coude
       ou dans un mouchoir à usage unique et le jeter, éviter de serrer la main et de se
        tenir à moins d'un mètre d'autrui. </text>
        
    <text class="confinement">Pourquoi se confiner ?</text>

    <text class="ressources">Ressources</text>
    <text class="malade">Si je pense être malade</text>

  </view>

</template>

<style>
.container {
  background-color: #980740;
  align-items: center;
  justify-content: center;
  flex: 1;
}
.gestes_barrières {
  color: white;
  font-size : 20;
  position: absolute;
  top: 120px;
  left : 10px
}
.confinement {
  color: white;
  font-size : 20;
  position: absolute;
  top: 240px;
  left : 10px
}
.ressources{
  color: white;
  font-size : 20;
  position: absolute;
  top: 360px;
  left : 10px
}
.malade{
  color: white;
  font-size : 20;
  position: absolute;
  top: 480px;
  left : 10px
}
.virus{
  border-color : black;
  background-color: #4CAF50; 
  padding : 100px;
  width:90px;
  height :90px;
  position : absolute;
  top : 500px;
  left: 10px;
}

</style>

