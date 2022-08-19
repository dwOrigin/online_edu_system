<template>
  <div class="card">
    <el-link :underline="false" style="font-size: 18px; font-weight: bold">
      <span v-if="type=='qa'">坐火车时座位被孕妇占座，孕妇示意自己是孕妇理直气壮，乘务员偏袒孕妇，遇到这种情况该怎么办？</span>
      <span v-if="type=='passage'">生命是一朵常开不败的花朵</span>
    </el-link>
    <div class="body">
      <el-link :underline="false" type="info">
        <p class="answer-preview" v-if="type=='qa'">
          燕子:&nbsp;&nbsp;&nbsp;这样看来， 邓拓在不经意间这样说过，越是没有本领的就越加自命不凡。这不禁令我深思。 我们不得不面对一个非常尴尬的事实，那就是， 一般来说，
          所谓中午吃什么，关键是中午吃什么需要如何写。
          伏尔泰说过一句富有哲理的话，坚持意志伟大的事业需要始终不渝的精神。这启发了我， 那么， 对我个人而言，中午吃什么不仅仅是一个重大的事件，还可能会改变我的人生。 这样看来，
          我们一般认为，抓住了问题的关键，其他一切则会迎刃而解。
          一般来讲，我们都必须务必慎重的考虑考虑。 黑格尔说过一句富有哲理的话，只有永远躺在泥坑里的人，才不会再掉进坑里。这启发了我， 我们不得不面对一个非常尴尬的事实，那就是， 既然如何，
          在这种困难的抉择下，本人思来想去，寝食难安。
        </p>
        <p class="answer-preview" v-if="type=='passage'">
          伏尔泰说过一句富有哲理的话，坚持意志伟大的事业需要始终不渝的精神。这启发了我， 那么， 对我个人而言，中午吃什么不仅仅是一个重大的事件，还可能会改变我的人生。 这样看来，
          我们一般认为，抓住了问题的关键，其他一切则会迎刃而解。
          一般来讲，我们都必须务必慎重的考虑考虑。 黑格尔说过一句富有哲理的话，只有永远躺在泥坑里的人，才不会再掉进坑里。这启发了我， 我们不得不面对一个非常尴尬的事实，那就是， 既然如何，
          在这种困难的抉择下，本人思来想去，寝食难安。
        </p>
      </el-link>
    </div>
    <div class="question-card-footer">
      <el-tag v-if="type=='qa'">
        &nbsp;&nbsp;16万赞同&nbsp;&nbsp;
      </el-tag>
      <el-tag v-if="type=='passage'">
        &nbsp;&nbsp;16万点赞&nbsp;&nbsp;
      </el-tag>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <el-tag type="success" v-if="type=='qa'">&nbsp;&nbsp;461条答案</el-tag>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuestionCard",
  props: {
    type: {
      default: 'qa',
      type: String,
    }
  }
}
</script>

<style scoped>
.card{
  border-bottom: 1px solid #9499A0;
  margin-top: 15px;
}
.body{
  margin: 10px auto;
}

.question-card-footer {
  height: 50px;
  margin-top: 6px;
}

.answer-preview {
  color: black;
  font-size: 15px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
</style>