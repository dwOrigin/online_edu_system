<template>
  <div class="p-center">
    <div class="inner">
      <el-col :span="5" class="nav" :offset="1">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-document"></i>
            <span slot="title">历史观看</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-document"></i>
            <span slot="title">收藏</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-document"></i>
            <span slot="title">私信</span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-setting"></i>
            <span slot="title">我的问题</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="16" class="rv" :offset="1">
        <div><span style="font-size: 22px;
                    line-height: 1.3;
                     margin-bottom: 20px;">历史观看</span></div>
        <div style="margin-top: 20px;">
<!--          <p-info></p-info>-->
          <history></history>
        </div>
      </el-col>
    </div>
  </div>
</template>

<script>
import PInfo from "@/components/user/personal/PInfo";
import History from "@/components/user/personal/History";

export default {
  name: "PersonalCenter",
  components: {
    PInfo,
    History
  }
}
</script>

<style scoped>
.inner{
  width: 1000px;
  margin: 0 auto 0 auto;
  /*height: max-content;*/
  overflow: auto;
}
.nav {
  border: 1px solid #E0E0E0;
  position: sticky;
  top: 500px;
  height: auto;
}

.rv {
}

.p-center {
  background-color: #f4f4f4;
  /*margin-top: 50px;*/
  height: max-content;
  padding-top: 50px;
  min-height: fit-content;
}
</style>