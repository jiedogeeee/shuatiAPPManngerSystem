import VueCookies from 'vue-cookies'

const userInfoKey = "loginInfo";

const getLoginInfo = () => {
    const loginInfo = VueCookies.get(userInfoKey);
    return loginInfo
}

const setLoginInfo = (loginInfo) => {
    delete loginInfo.menuList;
    VueCookies.set(userInfoKey, loginInfo, 0);
}

const removeLoginInfo = () => {
    VueCookies.remove(userInfoKey);
}

export {
    getLoginInfo,
    setLoginInfo,
    removeLoginInfo
};