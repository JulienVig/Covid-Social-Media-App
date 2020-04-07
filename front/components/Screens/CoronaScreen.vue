<template>
<scroll-view>
  <view class="container">
    <text class="title">Les Gestes Barrières</text>
    <text class="para">Il est important de régulièrement se laver les mains, tousser dans son coude
       ou dans un mouchoir à usage unique et le jeter, éviter de serrer la main et de se
        tenir à moins d'un mètre d'autrui. </text>
        <href color = "darkblue" url="https://www.who.int/fr/emergencies/diseases/novel-coronavirus-2019/advice-for-public" name="Gestes barrières" />



    <text class="title">Pourquoi se confiner </text>
    <text class="para">Se confiner vous protège vous, mais aussi vos proches ainsi que le reste de la population. Le confinement aide à ne pas surchager les hopitaux. En restant chez vous, vous sauvez des vies. </text>

    <text class="title">En finir avec les idées reçues :  </text>
    <text class="para">Beaucoup de fausses informations à propos du nouveau coronavirus sont en circulation, pour démêler le vrai du faux : </text>
    <href color = "darkblue" url="https://www.who.int/fr/emergencies/diseases/novel-coronavirus-2019/advice-for-public/myth-busters" name="Liste de fausses informations" />

    <text class="title">Ressources  </text>
    <text class="para">Situation en Suisse et à l'étranger :  </text>
    <href color = "darkblue" url="https://www.bag.admin.ch/bag/fr/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/situation-schweiz-und-international.html" name="Suisse" />
    <text class="para">Situation en France et à l'étranger : </text>
    <href color = "darkblue" url="https://solidarites-sante.gouv.fr/soins-et-maladies/maladies/maladies-infectieuses/coronavirus/etat-des-lieux-et-actualites/" name="France" />


    <text class="title">Si je pense être malade  </text>
    <text class="para">Est-ce que j'ai le coronavirus?</text>
    <href color = "darkblue" url="https://ofsp-coronavirus.ch/check/" name="Suisse" />
    <href color = "darkblue" url="https://solidarites-sante.gouv.fr/soins-et-maladies/maladies/maladies-infectieuses/coronavirus/tout-savoir-sur-le-covid-19/article/est-ce-que-j-ai-le-coronavirus-429321" name="France" />
    <text class="para">  Je suis malade, que faire? </text>
    <href color = "darkblue" url="https://www.bag.admin.ch/bag/fr/home/krankheiten/ausbrueche-epidemien-pandemien/aktuelle-ausbrueche-epidemien/novel-cov/selbst-isolierung-und-selbst-quarantaene.html" name="Suisse" />
    <href color = "darkblue" url="https://solidarites-sante.gouv.fr/soins-et-maladies/maladies/maladies-infectieuses/coronavirus/tout-savoir-sur-le-covid-19/article/j-ai-des-symptomes-je-suis-malade-covid-19" name="France" />
    
    <text class="title">Si j'ai besoin de parler à quelqu'un  </text>
    <text class="para">L'isolement peut être compliqué moralement. Il ne faut pas rester seul.e face à des idées noires.</text>
    <href color = "darkblue" url=" https://www.santepsy.ch/fr/" name="Suisse" />
    <text class="para">Ou appeler le 143  </text>
    <href color = "darkblue" url="http://www.psycom.org/Urgences-psychiatriques#qfecdu" name="France" />
    <text class="para">Ou appeler SOS Psychiatrie 01 47 07 24 24  </text>

    <text class="title">Si je me sens en danger à la maison, je cherche de l'aide ou de la protection  </text>
    <text class="para">Il est déconseillé de sortir, il n'est pas interdit de fuir. Victime de violence?  </text>
    <href color = "darkblue" url="https://www.aide-aux-victimes.ch/en/where-can-i-find-support/" name="Suisse" />
    <text class="para">En cas d'urgence, appeler le 117    </text>
    <text class="para">France : appeler le 3919, en cas d'urgence, appeler le 17   </text>
  </view>
</scroll-view>
</template>

<style>
.container {
  background-color: #980740;
  align-items: center;
  justify-content: center;
  flex: 1;
}
.title {
  color: white;
  font-size : 20;
  margin : 2%;
  width: 80%;
}
.para {
  color: white;
  font-size: 15;
  width : 80%;

}



.gestes_barrières {
  color: white;
  font-size : 20;
  margin : 2%;
  /*position: absolute;
  top: 120px;
  left : 10px */
}
.para_g_b {
  color: white;
  font-size: 15;

}
.confinement {
  color: white;
  font-size : 20;
  margin : 5%;
  /*position: absolute;
  top: 240px;
  left : 10px*/
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
<script>
import href from "../href.vue";
export default {
  components : {
    href
  }

}
</script>
