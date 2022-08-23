<template>
<div
    @scroll="scrollBottom"
    ref="div"
    class="fixWindow">
  <navigation></navigation>
  <HomeMain></HomeMain>
  <home-footer></home-footer>
</div>
</template>



<script>
import Navigation from './home/Navigation';
import HomeMain from "@/components/user/home/HomeMain";
import HomeFooter from "@/components/user/home/HomeFooter";
export default {
  name: "Home",
  components:{
    Navigation,
    HomeMain,
    HomeFooter
  },
  methods:{
    scrollBottom(){
      let sT = this.$refs.div.scrollTop;
      let sH = this.$refs.div.scrollHeight;
      let cH = this.$refs.div.clientHeight;
      if(sT + cH >= sH){
        this.$bus.$emit('scrollToBottom');
      }
    }
  },
  mounted() {
    this.$router.replace({
      name: 'recommend'
    });
  },
}

</script>

<style scoped>
.fixWindow{
  width: 100vw;
  height: 100vh;
  overflow: auto;
}
</style>