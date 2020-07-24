## 高效工作库

[![](https://jitpack.io/v/LiHang941/work-tool.svg)](https://jitpack.io/#LiHang941/work-tool)




```
tool-lib   常用工具 
tool-redis redis工具
tool-spring spring工具
```


## 使用

推荐gradle 

```
// 添加仓库
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
  implementation "com.github.LiHang941.work-tool:tool-all:$workToolVersion" 
  implementation "com.github.LiHang941.work-tool:tool-lib:$workToolVersion" 
  implementation "com.github.LiHang941.work-tool:tool-redis:$workToolVersion" 
  implementation "com.github.LiHang941.work-tool:tool-spring:$workToolVersion" 
}
```



