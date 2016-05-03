# ShowCaseView
一个新手指引的View

##demo
<table sytle="border: 0px;">
<tr>
<td><img width="200px" src="https://github.com/corerzhang/ShowCaseView/raw/master/screenshot/showcase.gif" /></td>
</tr>
</table>

##使用
```java
         View guideView = LayoutInflater.from(this).inflate(R.layout.showcase_content, null, false);
         View target = findViewById(R.id.target);
         ShowCaseView showCaseView = new ShowCaseView.Builder(this)
                .setAnimationController(new AnimationController())
                .setLayoutController(new LayoutController(guideView))
                .setShape(new RectangleShape())
                .build(target);
        
        showCaseView.show(this);
        showCaseView.hide();
        
```

## 支持拓展

###拓展绘制高亮区域的样式
>setShape(IShape shape)

###拓展布局控制
>setLayoutController(ILayoutController layoutController)

###拓展显示和隐藏的动画
>setAnimationController(IAnimationController animationController)



