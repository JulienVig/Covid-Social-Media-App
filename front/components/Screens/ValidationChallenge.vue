<template>
  <view class ="container">
    <text class="title">Validation du défi :</text>

    <text class="subTitle">{{defiTitle}}</text> <!-- récupérer le nom du challenge -->

    

    <!-- importer un fichier c'est de la grosse merde <-->

    <!-- <button :on-press="onPressEvent"
    title="Importer une photo"
    color="#841584"
    accessibility-label="Importer une photo depuis l'appareil"/> -->

   <button
    :on-press="() => pickImage()"
    title="Importer une image depuis l'appareil"
    />
    <button
    :on-press="() => takePhoto()"
    title="Prendre une photo avec l'appareil"
    />

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
      Alert.alert('Là on confirme', 'à implémenter')
    },
    pickImage : async () => {
            const {
              status: cameraRollPerm
            } = await Permissions.askAsync(Permissions.CAMERA_ROLL);

            // only if user allows permission to camera roll
            if (cameraRollPerm === 'granted') {
              let pickerResult = await ImagePicker.launchImageLibraryAsync({
                allowsEditing: true,
                aspect: [4, 3],
              });

              // handleImagePicked(pickerResult);
            }
        },
        takePhoto : async () => {
           const {
             status: cameraPerm
           } = await Permissions.askAsync(Permissions.CAMERA);

           const {
             status: cameraRollPerm
           } = await Permissions.askAsync(Permissions.CAMERA_ROLL);

           // only if user allows permission to camera AND camera roll
           if (cameraPerm === 'granted' && cameraRollPerm === 'granted') {
             let pickerResult = await ImagePicker.launchCameraAsync({
               allowsEditing: true,
               aspect: [4, 3],
             });

             // handleImagePicked(pickerResult);
           }
       },
       login : function() {
          var bodyFormData = new FormData();
            bodyFormData.append('username', this.username);
            bodyFormData.append('password', this.password);
          API({
            method: 'post',
            url: '/login',
            data: bodyFormData,
            headers: {'Content-Type': 'multipart/form-data' }
          }).then(function(response){
            console.log(response)
        })
    },
  
  },

};

</script>
