const regs = {
    email: /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/,
    number: /^([0]|[1-9][0-9]*)$/,
    password: /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z~!@#$%^&*_]{8,}$/,
    version: /^[0-9\.]+$/,
    phone: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/
}
const verify = (rule, value, reg, callback) => {
    if (value) {
        if (reg.test(value)) {
            callback()
        } else {
            callback(new Error(rule.message))
        }
    } else {
        callback()
    }
}

export default {
    email: (rule, value, callback) => {
        return verify(rule, value, regs.email, callback)
    },
    number: (rule, value, callback) => {
        return verify(rule, value, regs.number, callback)
    },
    password: (rule, value, callback) => {
        return verify(rule, value, regs.password, callback)
    },
    version: (rule, value, callback) => {
        return verify(rule, value, regs.version, callback)
    },
    phone: (rule, value, callback) => {
        return verify(rule, value, regs.phone, callback)
    },
}
