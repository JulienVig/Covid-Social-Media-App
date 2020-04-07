<template>
    <view class="real-container">
        <view class="container">
            <view class="header">
              <text class="title">Catégories</text>
            </view>
            <view class ="main">
                <view class="categories">
                        <view class="category" v-for="(category, index) in categories" :key="index">
                            <touchable-opacity class="single-element-container" :on-press="() => goToCategory(category.id)">
                                <image
                                  :style="{width: 50, height: 50}"
                                  :source="{uri: 'http://192.168.43.78:8080/static/image/jpg?path=' + category.logo}"
                                />
                                <text class="name">{{category.name}}</text>
                            </touchable-opacity>
                        </view>
                </view>
            </view>
        </view>
  </view>
</template>

<style>

.header{
    width: 100%;
    height: 10%;
    justify-content: center;
    align-items: center;
    /* margin-bottom: 50; */
    background-color: #3d9d84;
}

.title{
    color: white;
    font-size: 40;
    font-weight: 300;
    /* margin-left: 50; */
}

.icon {
  height:100;
  width:100;
  /* border-radius: 50%; */
}

.main {
    justify-content: center;
    align-items: center;
    height:90%;
}
.categories{
    padding-top: 50px;
    padding-bottom: 50px;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    align-content: center;
    flex-wrap: wrap;
    max-width: 90%;
    height:80%;
    background-color: grey;
}
.single-element-container{
    justify-content: center;
    margin: 10px;
    width: 80;
}

.name{
    justify-content: center;
    align-items: center;
    text-align: center;
    font-weight:200;
    color:#2c3c74;
}

.container {
    background-color: #b2ebcc;
    width:100%;
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
            console.log(error)
        })
    },
    setLogos(){

    },
    goToCategory(catId) {
      this.navigation.navigate("ChallengesByCategory", {categoryId:catId})
    },

  },
  mounted: function() {
      this.fetch();
  }
};
</script>
