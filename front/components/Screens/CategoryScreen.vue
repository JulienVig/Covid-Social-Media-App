<template>
    <view class="real-container">
        <view class="container">
            <view class="header">
              <text class="title">Catégories</text>
            </view>
            <view class ="main">
                <view class="categories">
                        <view class="category" v-for="category in categories" :key="category.id">
                            <view class="single-element-container" :on-press="goToCategory(category.id)">
                                <image
                                  :style="{width: 50, height: 50}"
                                  :source="{uri: 'http://192.168.1.17:8080/banana'}"
                                />
                                <text class="name">{{category.name}}</text>
                            </view>
                        </view>
                </view>
            </view>
        </view>
  </view>
</template>

<style>

.header{
    width: 100%;
    justify-content: center;
    align-items: center;
}

.title{
    background-color: #888888;
}

.icon {
  height:80;
  width:80;
  /* border-radius: 50%; */
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
    height:100%;
}
.scrollable{
    background-color: #EEEEEE;
    flex-direction: row;
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

.container {
    background-color: #b2ebcc;
  width:100%;
}

.title{
    color: #3d9d84;
    font-size: 40;
    font-weight: 800;
    margin-bottom: 10;
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
                name: "Hardcodé 1",
            },
            {
                name: "Hardcodé 2",
            },
        ]
    }
  },
  methods: {
    fetch () {
     console.log("Fetch !")
        const self = this;
       API({
        method: 'get',
        url: '/api/allCategories',
        }).then(function(response){
            self.categories = response.data;
            // this.setLogos();
            console.log('Fetched all categories')


        }).catch(function(error){
            console.log("==================      ERROR       ==================")
           console.log(error)
        })
    },
    setLogos(){

    },
    goToCategory(categoryId) {
      this.navigation.navigate("ChallengesByCategory", {challengeId:categoryId})
    },

  },
  mounted: function() {
      this.fetch();
  }
};
</script>
