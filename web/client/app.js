//app.js
App({
  globalData: {
    userInfo: {}
  },
  onLaunch: function () {
    wx.setStorageSync('flag', 'true'),
    wx.getUserInfo({
      success: function (res) {
        var userInfo = res.userInfo
        var nickName = userInfo.nickName
        var avatarUrl = userInfo.avatarUrl
        var gender = userInfo.gender
        var province = userInfo.province
        var city = userInfo.city
        var country = userInfo.country
      }
    })
  },
  getUserInfo: function (cb) {
    var that = this
    //调用登录接口
    wx.login({
      success: function () {
        wx.getUserInfo({
           success: function (res) {
             that.globalData.userInfo = res.userInfo
             typeof cb == "function" && cb(that.globalData.userInfo)
          }
        })
      }
    })
  }
})