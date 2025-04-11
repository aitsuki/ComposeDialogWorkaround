# Compose dialog workaround

Compose 中的 Dialog 和 BottomSheet 始终存在一些奇怪的问题，而这个仓库就是为了解决这些问题存在的，仓库中的代码已在
生产环境验证过。 

- Full Screen Dialog
- Bottom Dialog
- 适配 edge-to-edge


## Compose Dialog 问题收集

1. Compose UI 1.7 版本之前 AdjustResize 无效的Bug：[Android Compose Dialog AdjustResize 无效的解决方案](https://aitsuki.com/blog/android-compose-dialog-adjust-resize-workaround/)=

2. Compose UI 1.7 之后虽然解决了 AdjustResize，但又出现全屏Dialog无法正确响应 edge-to-edge 的问题。

3. ModalBottomSheet 目前没有提供禁止拖拽的功能，使用起来配置过于麻烦，而且容易出现状态不一致的bug。
