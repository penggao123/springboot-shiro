# SpringBoot + shiro

### 一：认证及授权
1、AuthorizingRealm:授权
2、AuthenticatingRealm:认证
总结：AuthorizingRealm继承AuthenticatingRealm

### 二：多realm认证
1、只需要继承AuthenticatingRealm类
2、多realm认证策略如下三种:
        AllSuccessfulStrategy:多个realm都认证通过才能认证成功
        AtLeastOneSuccessfulStrategy(默认)：多个realm有一个认证通过就能认证成功
        FirstSuccessfulStrategy：多个realm中，第一个realm成功才能认证成功