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

export default {
    methods:{
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
    }
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
