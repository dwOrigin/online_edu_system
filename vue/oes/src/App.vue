<template>
  <div id="app">
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {},
  provide(){
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      isRouterAlive:true
    }
  },
  methods:{
    reload(){
      this.isRouterAlive=false;
      this.$nextTick(function(){
        this.isRouterAlive=true
      })
    }
  },
  // provide(){
  //   return{
  //     reload:this.reload
  //   }
  // }
  // 据说这样刷新页面效果最好
  // mounted() {
  //   this.$router.replace({
  //     name: 'recommend'
  //   });
  // }

}
</script>

<style>
body{
  font-family: Helvetica, Tahoma, Arial, STXihei, “华文细黑”, “Microsoft YaHei”, “微软雅黑”, sans-serif;
}
</style>
