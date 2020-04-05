<template>
  <view class ="container">
    <text class="title">Validation du défi :</text>

    <text class="subTitle">{{defiTitle}}</text> <!-- récupérer le nom du challenge -->

    

    <!-- importer un fichier c'est de la grosse merde <-->

    <button :on-press="onPressEvent"
    title="Importer une photo"
    color="#841584"
    accessibility-label="Importer une photo depuis l'appareil"/>

  
    <text class="box">Ajouter une description : </text>
     <text-input class="insideBox">{{description}}</text-input>

    <button :on-press="onPressEvent"
    title="Confirmer"
    color="#841584"

    accessibility-label="Confirmer la validation du défi"/>
</view>
</template>

<style>
.container {
  background-color: white;
  display: flex;
  font-size: 32;
  margin: auto;
  color: blue;
}
.title {
font-size : 100;
margin: auto;
text-align:center;
color: #19c059;
}
.subTitle {
font-size : 80;
margin: auto;
text-align:center;
color: #19c059;
}

.box {
font-size : 30;
margin: auto;
text-align:center;
color: #19c059;
}

.insideBox {
font-size : 30;
right: 30px;
left: 30px;
margin: auto;
text-align:center;
color: #19c0c9;
}

</style>

<script>
import { Alert } from 'react-native';
export default {
  data: function() {
    return {
        defiTitle : "Nom du défi",
        description : "Ecrire ici..."

    }
  },
  methods: {
      onPressEvent : function() {
      this.password = this.username;
      Alert.alert('oui', 'oui')
      console.log('test clicked')
    }
  },

};

</script>
