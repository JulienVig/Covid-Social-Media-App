<template>
    <view class="real-container">
        <view class="container">
            <view class="header"
              <text class="title">Catégories</text>
            </view>
            <view class ="main">
                <view class="categories">
                    <view class="category" v-for="(category, id) in categories" :key="id">
                        <view class="single-element-container">
                            <image class = "corona-icon" :source="require('../../assets/images/challengescreen/virus-lab-scientist-biology-cell-medical-512.png')"/>
                            <text class="name">{{category.name}}</text>
                        </view>
                    </view>
                </view>
            </view>
        </view>
  </view>
</template>

<style>

.corona-icon {
  height:80;
  width:80;
}

.main {
    justify-content: center;
    align-items: center;
}
.categories{

    /* margin: 10px; */
    flex-direction: row;
    justify-content: space-around;
    flex-wrap: wrap;
    max-width: 90%;
}

.single-element-container{
    justify-content: center;
    align-items: center;
    margin: 10px;
    max-width: 100px;
    width: 90%;
}

.name{
    justify-content: center;
    align-items: center;
}

.real-container {
    /* align-items: center;
    justify-content: center;
    flex:1; */
}
.container {
  background-color: white;
  width:80%;
}

.title{
    font-size: 40;
    font-weight: 100;
    margin-bottom: 100;
}

</style>

<script>
import {API} from '../../api.js';
import { Alert } from 'react-native';
import axios from "axios";

export default {
    props: {
      navigation: {
        type: Object
      }
    },
  data: function() {
    return {
        categories: [
            {
                name: "Jardinage de tulipe",
            },
            {
                name: "Dessin",
            },
            {
                name: "Bricolage",
            },
            {
                name: "Musique",
            },
            {
                name: "Entraide",
            },
            {
                name: "Nettoyage",
            }
        ]
    }
  },
  methods: {
    login () {
     console.log("Login !")
      var bodyFormData = new FormData();
        bodyFormData.append('username', this.username);
        bodyFormData.append('password', this.password);
        const self = this;
       API({
        method: 'post',
        url: '/api/allCategories',
        data: bodyFormData,
        headers: {'Content-Type': 'multipart/form-data' }
        }).then(function(response){
           //console.log(response)
          if(response != undefined && response.status == 200){
              self.navigation.navigate("Challenges")
          }
        }).catch(function(error){
           console.log(error)
        })
    },
    goToTabNavigator() {
      this.navigation.navigate("Challenges")
    }
  },
  mounted: function() {

  }
};
</script>
