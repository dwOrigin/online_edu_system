<template>
  <div class="p-center">
    <div class="inner">
      <div class="nav">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
          <el-menu-item index="1">
            <i class="el-icon-user"></i>
            <span slot="title">
              个人信息
            </span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-document"></i>
            <span slot="title">历史观看</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-star-off"></i>
            <span slot="title">收藏</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-chat-dot-square"></i>
            <span slot="title">
              私信
              <el-badge class="mark" :value="122" style="margin-bottom: 10px" :max="99"/>
            </span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-question"></i>
            <span slot="title">我的提问</span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="el-icon-back"></i>
            <span slot="title">退出登录</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div class="rv">
<!--        <div><span style="font-size: 22px;-->
<!--                    line-height: 1.3;-->
<!--                     margin-bottom: 20px;">历史观看</span></div>-->
        <div style="margin-top: 20px;">
<!--          <p-info></p-info>-->
<!--          <history></history>-->
<!--          <star></star>-->
<!--          <message-center></message-center>-->
          <my-question></my-question>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PInfo from "@/components/user/personal/PInfo";
import History from "@/components/user/personal/History";
import Star from "@/components/user/personal/Star";
import MessageCenter from "@/components/user/personal/MessageCenter";
import MyQuestion from "@/components/user/personal/MyQuestion";
export default {
  name: "PersonalCenter",
  components: {
    PInfo,
    History,
    Star,
    MessageCenter,
    MyQuestion
  }
}
</script>

<style scoped>
.inner{
  width: 1000px;
  margin: 0 auto;
  display: flex;
}
.nav {
  border: 1px solid #E0E0E0;
  width: 200px;
  margin-left: 50px;
  margin-right: 40px;
  height: max-content;
  position: sticky;
  top: 25%;
}

.rv {
  width: 550px;
}

.p-center {
  background-color: #f4f4f4;
  padding-top: 50px;
  margin-bottom: -50px;
}
</style>