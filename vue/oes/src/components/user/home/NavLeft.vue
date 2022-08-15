<template>
  <div class="left-nav-box">
    <h2 style="color: teal">在线教育系统</h2>
    <div class="category-dropdown">
      <div>分类<i class="el-icon-tickets el-icon--right"></i></div>
      <div class="dropdown-content">
        <span class="first-category">IT技术</span>
        <div class="category-list">
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
        </div>
        <span class="first-category">考试</span>
        <div class="category-list">
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
        </div>
        <span class="first-category">职业技能</span>
        <div class="category-list">
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
        </div>
        <span class="first-category">兴趣生活</span>
        <div class="category-list">
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
          <div class="item">
            <el-button round size="small">圆角按钮</el-button>
          </div>
        </div>

      </div>
    </div>
    <el-input placeholder="请输入搜索内容" v-model="searchKey" class="input-with-select">
      <el-button type="primary" slot="suffix" class="search-button">搜索</el-button>
    </el-input>
  </div>
</template>

<script>
export default {
  name: "NavLeft",
  data(){
    return{
      select: '',
      searchKey: ''
    }
  }
}
</script>

<style scoped>
.search-button{
  transform: translateX(5px);
}

.input-with-select{
  width: 250px;
}

.item {
  width: 80px;
  margin: 5px 5px;
}

.first-category {
  font-weight: normal;
}

.left-nav-box {
  display: flex;
  align-items: center;
  height: 100%;
}

.category-dropdown {
  margin: 0 50px;
  position: relative;
  height: 100%;
  line-height: 80px;
}

.category-dropdown:hover {
  color: red;
}

.category-dropdown:hover .dropdown-content {
  display: block;
}

.dropdown-content {
  display: none;
  position: absolute;
  top: 80%;
  min-width: 500px;
  background-color: white;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  color: black;
  border-radius: 4px;
  line-height: normal;
  padding-left: 20px;
}

.category-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  width: 90%;
  margin: 10px 0;
}


</style>