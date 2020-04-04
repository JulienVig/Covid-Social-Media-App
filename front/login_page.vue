<template>
    <div id="login_page">
        <view class ="container">
                <h1> Enregistrement. </h1>
                
                <text class = "Title"> Nom d'utilsisateur </text>
                
                <text-input
                    :style = "text_field"
                    v-model = "username"
                />

                <text class = "Title"> Mot de passe </text>
                
                <text-input
                    :style = "text_field"
                    v-model = "password"
                />

                <button 
                    :on-press = "connect"
                    :title = "C'est parti!"
                />
        </view>
    </div>
</template>
            

<style>

.Title {
  color: blue;
}

.text_field{
    color: black;
}

</style>


<script>
    export default(){
        data : function(){
            return {
                username = 'Test',
                password = 'Test'
                };
            },
        methods : {
            connect : function(){
                // ici lancé requête vers la database pour *LOGIN*
            }
        }

</script>