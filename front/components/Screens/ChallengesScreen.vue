<template>
  <view class="container">
    <text class="heading">Challenge</text>
    <text>This is the Challenge screen</text>
    <button
    :on-press="() => pickImage()"
    title="Pick an image from camera roll"
    />
    <button
    :on-press="() => takePhoto()"
    title="Take a photo"
    />
  </view>
</template>

<script>
import * as ImagePicker from 'expo-image-picker';
import * as Permissions from 'expo-permissions';
import { Alert } from 'react-native';


export default {
    data: async function() {
      return {
          uploading: false,
          image:null,
      }
    },
    methods:{
        pickImage :  async function () {
            const {
              status: cameraRollPerm
            } = await Permissions.askAsync(Permissions.CAMERA_ROLL);

            // only if user allows permission to camera roll
            if (cameraRollPerm === 'granted') {
              let pickerResult = await ImagePicker.launchImageLibraryAsync({
                allowsEditing: true,
                aspect: [4, 3],
              });
              this.handleImagePicked(pickerResult);
          }
        },

        takePhoto : async function () {
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
             let uploadResponse, uploadResult;
             this.uploading = true;
             try {
                 if (!pickerResult.cancelled) {
                     //Send POST to server
                    uploadResponse = await uploadImageAsync(pickerResult.uri);
                    // uploadResult = await uploadResponse.json();
                    // this.image: uploadResult.location
                }
               } catch (e) {
                  console.log({ uploadResponse });
                 // console.log({ uploadResult });
                 console.log({ e });
                 Alert.alert('Upload failed, sorry :(');

               } finally {
                   this.uploading = false;
               }
             }
         },
         handleImagePicked : async function(pickerResult) {
            let uploadResponse, uploadResult;

            try {
              this.uploading = true;

              if (!pickerResult.cancelled) {
                uploadResponse = await uploadImageAsync(pickerResult.uri);
                // uploadResult = await uploadResponse.json();
                // image = uploadResult.location
              }
            } catch (e) {
              console.log({ uploadResponse });
              console.log({ uploadResult });
              console.log({ e });
              Alert.alert('Upload failed, sorry :(');
            } finally {
              this.uploading= false;
            }
          }

    }
}


async function uploadImageAsync(uri){
    Alert.alert('uploading')
    return 0;
  // let apiUrl = 'https://file-upload-example-backend-dkhqoilqqn.now.sh/upload';
  //
  // // Note:
  // // Uncomment this if you want to experiment with local server
  // //
  // // if (Constants.isDevice) {
  // //   apiUrl = `https://your-ngrok-subdomain.ngrok.io/upload`;
  // // } else {
  // //   apiUrl = `http://localhost:3000/upload`
  // // }
  //
  // let uriParts = uri.split('.');
  // let fileType = uriParts[uriParts.length - 1];
  //
  // let formData = new FormData();
  // formData.append('photo', {
  //   uri,
  //   name: `photo.${fileType}`,
  //   type: `image/${fileType}`,
  // });
  //
  // let options = {
  //   method: 'POST',
  //   body: formData,
  //   headers: {
  //     Accept: 'application/json',
  //     'Content-Type': 'multipart/form-data',
  //   },
  // };
  //
  // return fetch(apiUrl, options);
}
</script>
<style>
.container {
  align-items: center;
  justify-content: center;
  flex: 1;
}
.heading {
  font-size: 30px;
  font-weight: bold;
  color: darkolivegreen;
  margin: 20px;
}
</style>
