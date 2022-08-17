<template>
  <div class="comment">
    <div class="comment-header">
      <div class="comment-avatar">
        <el-avatar
            :size="40"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png">
        </el-avatar>
      </div>
      <div class="comment-info">
        <div style="font-size: 16px;">如天落云锦</div>
        <div class="time-and-score">
          <div style="margin-right: 10px">5年前</div>
          <el-rate
              v-model="value"
              disabled
              text-color="#ff9900"
              score-template="{value}">
          </el-rate>
        </div>
      </div>
    </div>
    <div class="comment-content">
      <p style="width: 100%">
        Lucy老师是我见过的唯一一位美丽大方善良且善解人意，钢琴弹的好，声音很好听，段子随手就来一句的小仙女，Lucy大大讲课很容易理解，更喜欢教给同学们学习方法和人生哲理，在教我英语时，也学到了一些生活知识，很感谢Lucy老师。
      </p>
    </div>
  </div>

</template>

<script>
export default {
  name: "Comment",
  data() {
    return {
      value: 3.7
    }
  }
}
</script>

<style scoped>
.comment-content{
  margin-left: 60px;
  font-size: 16px;
  line-height: 26px;
  color: #0f1419;
  word-break: break-word;
  height: 100px;
  /*background-color: teal;*/
}
.time-and-score {
  color: #99a9bf;
  font-size: 12px;
  margin-top: 5px;
  display: flex;
  align-items: center;
}

.comment-info {
  font-weight: lighter;
}

.comment-avatar {
  margin-right: 20px;
}

.comment-header {
  display: flex;
  margin-top: 10px;
}

.comment {
  border-bottom: 1px solid #edeff2;
  margin-top: 15px;
}
</style>