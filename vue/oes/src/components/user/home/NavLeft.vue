<template>
  <div class="left-nav-box">
    <h2
        @click="$router.push({
          name: 'recommend'
        })"
        style="color: teal" class="hoverC">在线教育系统</h2>
    <div class="category-dropdown">
      <div>分类<i class="el-icon-tickets el-icon--right"></i></div>
      <div class="dropdown-content">
        <span class="first-category">IT技术</span>
        <div class="category-list">
          <div class="item" v-for="tag in tagList.it">
            <el-button round size="small" @click="handleClickTag(tag)">{{ tag.title }}</el-button>
          </div>
        </div>
        <span class="first-category">考试</span>
        <div class="category-list">
          <div class="item" v-for="tag in tagList.exam">
            <el-button round size="small" @click="handleClickTag(tag)">{{ tag.title }}</el-button>
          </div>
        </div>
        <span class="first-category">职业技能</span>
        <div class="category-list">
          <div class="item" v-for="tag in tagList.skill">
            <el-button round size="small" @click="handleClickTag(tag)">{{ tag.title }}</el-button>
          </div>
        </div>
        <span class="first-category">兴趣生活</span>
        <div class="category-list">
          <div class="item" v-for="tag in tagList.hobby">
            <el-button round size="small" @click="handleClickTag(tag)">{{ tag.title }}</el-button>
          </div>
        </div>

      </div>
    </div>
    <el-input
        placeholder="请输入搜索内容"
        @keyup.enter.native="handleSearch"
        v-model="searchKey" class="input-with-select">
      <el-button type="primary" slot="suffix"
                 @click="handleSearch"
                 class="search-button">搜索
      </el-button>
    </el-input>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "NavLeft",
  data() {
    return {
      select: '',
      searchKey: '',
      //分类标签集合
      tagList: {
        it: [
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
        ],
        exam: [
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
        ],
        skill: [
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
        ],
        hobby: [
          {title: '分类标签'},
          {title: '分类标签'},
          {title: '分类标签'},
        ]
      }
    }
  },
  methods: {
    //点击分类栏分类标签触发!
    handleClickTag(tag) {
      this.select = tag.title;
      this.searchKey = '';
      this.handleSearch();
    },
    //点击搜索或enter触发
    handleSearch() {
      if (this.select === '' && this.searchKey === '') {
        return;
      }
      this.$router.push({
        name: 'search',
        params:{
          select: this.select || undefined,
          searchKey: this.searchKey || undefined
        }
      });
      this.$bus.$emit('searchInputChanged', this.select, this.searchKey);
      this.searchKey = '';
      this.select = '';
    }
  },
}
</script>

<style scoped>
.hoverC:hover {
  cursor: pointer;
}

.search-button {
  transform: translateX(5px);
}

.input-with-select {
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