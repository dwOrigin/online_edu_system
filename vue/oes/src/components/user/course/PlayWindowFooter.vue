<template>
<div class="play-window-footer">
  <div class="star-score">
    <div>
      <el-link :underline="false"
               type="primary"
               class="starLink"
               icon="el-icon-star-off"><span style="font-size: small">收藏课程</span></el-link>
    </div>
    <el-rate
        v-model="value"
        disabled
        show-score
        text-color="#ff9900"
        score-template="{value}">
    </el-rate>
  </div>
  <div class="comment-and-info-card">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane name="first">
        <div slot="label" class="title-tag">课程介绍</div>
        <p>
          换句话来说，你首先需要做的就是：明确机构/课程定位和目标客户人群。任何机构想要获得增长，
          关键就是了解自己的客户。他们的痛点是什么？你的课程方案可以解决他们什么样的教育问题？
          这些内容都需要映射在你的课程介绍中。在写课程名称时，只需要组合课程的核心内容和适用对象，
          外加语言的修饰和润色即可（务必简介易懂）。如果机构伙伴们对课程还没有准确的定位，
          首先要思考定位及想覆盖的客户人群。尽量用简洁的短语搭配来覆盖目标人群，如“专升本”、“职场新人”、
          “雅思零基础”、“摩托驾照周末班”、“10到15岁”等。
        </p>
        <p>
          让目标客户看到时，明确感知到这个课程与自己的需求匹配，从而进一步咨询留存电话线索。
          首先，我们要确认这个课程是否符合我们的教育需求。然后，我们会判断课程的价格范围是否符合我们的预期。
          接着，我们会继续课程有哪些亮点或独特优势，选择最“心仪”的课程。
          这就要求我们在课程详情页将以上内容都清晰、完整地进行介绍。
        </p>
      </el-tab-pane>
      <el-tab-pane name="second">
        <div slot="label"  class="title-tag">用户评论</div>
        <course-comment-list></course-comment-list>
      </el-tab-pane>
    </el-tabs>
  </div>
</div>
</template>

<script>
import CourseCommentList from "@/components/user/course/CourseCommentList";
export default {
  name: "PlayWindowFooter",
  components:{
    CourseCommentList
  },
  data(){
    return {
      value: 4
    }
  }
}
</script>

<style scoped>
.star-score{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
p{
  font-size: medium;
  line-height: 30px;
  color: #0f1419;
  word-break: break-word;
  margin: 10px auto;
  width: 80%;
  text-indent: 2em;
}
.title-tag{
  font-size: small;
  margin: 0 156px;
}
.comment-and-info-card{
  margin-top: 20px;
  border-radius: 5px;
  border: 2px solid #F1F2F3;
  box-shadow: 0 3px 12px rgb(0 36 153 / 6%);
}
.starLink{
  font-size: x-large;
}
.play-window-footer{
  margin-top: 30px;
  width: 100%;
  height: auto;
  /*background-color: teal;*/
}
</style>