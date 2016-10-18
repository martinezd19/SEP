/*
 * jQuery Easing v1.3 - http://gsgd.co.uk/sandbox/jquery/easing/
 *
 * Uses the built in easing capabilities added In jQuery 1.1
 * to offer multiple easing options
 *
 * TERMS OF USE - jQuery Easing
 * 
 * Open source under the BSD License. 
 * 
 * Copyright © 2008 George McGinley Smith
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of 
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * Neither the name of the author nor the names of contributors may be used to endorse 
 * or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE. 
 *
 */

// t: current time, b: begInnIng value, c: change In value, d: duration

jQuery.easing["jswing"] = jQuery.easing["swing"];
jQuery.extend(jQuery.easing, {
    def: "easeOutQuad", swing: function (e, t, n, r, i) {
        return jQuery.easing[jQuery.easing.def](e, t, n, r, i)
    }, easeInQuad: function (e, t, n, r, i) {
        return r * (t /= i) * t + n
    }, easeOutQuad: function (e, t, n, r, i) {
        return -r * (t /= i) * (t - 2) + n
    }, easeInOutQuad: function (e, t, n, r, i) {
        if ((t /= i / 2) < 1)return r / 2 * t * t + n;
        return -r / 2 * (--t * (t - 2) - 1) + n
    }, easeInCubic: function (e, t, n, r, i) {
        return r * (t /= i) * t * t + n
    }, easeOutCubic: function (e, t, n, r, i) {
        return r * ((t = t / i - 1) * t * t + 1) + n
    }, easeInOutCubic: function (e, t, n, r, i) {
        if ((t /= i / 2) < 1)return r / 2 * t * t * t + n;
        return r / 2 * ((t -= 2) * t * t + 2) + n
    }, easeInQuart: function (e, t, n, r, i) {
        return r * (t /= i) * t * t * t + n
    }, easeOutQuart: function (e, t, n, r, i) {
        return -r * ((t = t / i - 1) * t * t * t - 1) + n
    }, easeInOutQuart: function (e, t, n, r, i) {
        if ((t /= i / 2) < 1)return r / 2 * t * t * t * t + n;
        return -r / 2 * ((t -= 2) * t * t * t - 2) + n
    }, easeInQuint: function (e, t, n, r, i) {
        return r * (t /= i) * t * t * t * t + n
    }, easeOutQuint: function (e, t, n, r, i) {
        return r * ((t = t / i - 1) * t * t * t * t + 1) + n
    }, easeInOutQuint: function (e, t, n, r, i) {
        if ((t /= i / 2) < 1)return r / 2 * t * t * t * t * t + n;
        return r / 2 * ((t -= 2) * t * t * t * t + 2) + n
    }, easeInSine: function (e, t, n, r, i) {
        return -r * Math.cos(t / i * (Math.PI / 2)) + r + n
    }, easeOutSine: function (e, t, n, r, i) {
        return r * Math.sin(t / i * (Math.PI / 2)) + n
    }, easeInOutSine: function (e, t, n, r, i) {
        return -r / 2 * (Math.cos(Math.PI * t / i) - 1) + n
    }, easeInExpo: function (e, t, n, r, i) {
        return t == 0 ? n : r * Math.pow(2, 10 * (t / i - 1)) + n
    }, easeOutExpo: function (e, t, n, r, i) {
        return t == i ? n + r : r * (-Math.pow(2, -10 * t / i) + 1) + n
    }, easeInOutExpo: function (e, t, n, r, i) {
        if (t == 0)return n;
        if (t == i)return n + r;
        if ((t /= i / 2) < 1)return r / 2 * Math.pow(2, 10 * (t - 1)) + n;
        return r / 2 * (-Math.pow(2, -10 * --t) + 2) + n
    }, easeInCirc: function (e, t, n, r, i) {
        return -r * (Math.sqrt(1 - (t /= i) * t) - 1) + n
    }, easeOutCirc: function (e, t, n, r, i) {
        return r * Math.sqrt(1 - (t = t / i - 1) * t) + n
    }, easeInOutCirc: function (e, t, n, r, i) {
        if ((t /= i / 2) < 1)return -r / 2 * (Math.sqrt(1 - t * t) - 1) + n;
        return r / 2 * (Math.sqrt(1 - (t -= 2) * t) + 1) + n
    }, easeInElastic: function (e, t, n, r, i) {
        var s = 1.70158;
        var o = 0;
        var u = r;
        if (t == 0)return n;
        if ((t /= i) == 1)return n + r;
        if (!o)o = i * .3;
        if (u < Math.abs(r)) {
            u = r;
            var s = o / 4
        } else var s = o / (2 * Math.PI) * Math.asin(r / u);
        return -(u * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * i - s) * 2 * Math.PI / o)) + n
    }, easeOutElastic: function (e, t, n, r, i) {
        var s = 1.70158;
        var o = 0;
        var u = r;
        if (t == 0)return n;
        if ((t /= i) == 1)return n + r;
        if (!o)o = i * .3;
        if (u < Math.abs(r)) {
            u = r;
            var s = o / 4
        } else var s = o / (2 * Math.PI) * Math.asin(r / u);
        return u * Math.pow(2, -10 * t) * Math.sin((t * i - s) * 2 * Math.PI / o) + r + n
    }, easeInOutElastic: function (e, t, n, r, i) {
        var s = 1.70158;
        var o = 0;
        var u = r;
        if (t == 0)return n;
        if ((t /= i / 2) == 2)return n + r;
        if (!o)o = i * .3 * 1.5;
        if (u < Math.abs(r)) {
            u = r;
            var s = o / 4
        } else var s = o / (2 * Math.PI) * Math.asin(r / u);
        if (t < 1)return -.5 * u * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * i - s) * 2 * Math.PI / o) + n;
        return u * Math.pow(2, -10 * (t -= 1)) * Math.sin((t * i - s) * 2 * Math.PI / o) * .5 + r + n
    }, easeInBack: function (e, t, n, r, i, s) {
        if (s == undefined)s = 1.70158;
        return r * (t /= i) * t * ((s + 1) * t - s) + n
    }, easeOutBack: function (e, t, n, r, i, s) {
        if (s == undefined)s = 1.70158;
        return r * ((t = t / i - 1) * t * ((s + 1) * t + s) + 1) + n
    }, easeInOutBack: function (e, t, n, r, i, s) {
        if (s == undefined)s = 1.70158;
        if ((t /= i / 2) < 1)return r / 2 * t * t * (((s *= 1.525) + 1) * t - s) + n;
        return r / 2 * ((t -= 2) * t * (((s *= 1.525) + 1) * t + s) + 2) + n
    }, easeInBounce: function (e, t, n, r, i) {
        return r - jQuery.easing.easeOutBounce(e, i - t, 0, r, i) + n
    }, easeOutBounce: function (e, t, n, r, i) {
        if ((t /= i) < 1 / 2.75) {
            return r * 7.5625 * t * t + n
        } else if (t < 2 / 2.75) {
            return r * (7.5625 * (t -= 1.5 / 2.75) * t + .75) + n
        } else if (t < 2.5 / 2.75) {
            return r * (7.5625 * (t -= 2.25 / 2.75) * t + .9375) + n
        } else {
            return r * (7.5625 * (t -= 2.625 / 2.75) * t + .984375) + n
        }
    }, easeInOutBounce: function (e, t, n, r, i) {
        if (t < i / 2)return jQuery.easing.easeInBounce(e, t * 2, 0, r, i) * .5 + n;
        return jQuery.easing.easeOutBounce(e, t * 2 - i, 0, r, i) * .5 + r * .5 + n
    }
})

/*
 * jQuery throttle / debounce - v1.1 - 3/7/2010
 * http://benalman.com/projects/jquery-throttle-debounce-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
;
(function (b, c) {
    var $ = b.jQuery || b.Cowboy || (b.Cowboy = {}), a;
    $.throttle = a = function (e, f, j, i) {
        var h, d = 0;
        if (typeof f !== "boolean") {
            i = j;
            j = f;
            f = c
        }
        function g() {
            var o = this, m = +new Date() - d, n = arguments;

            function l() {
                d = +new Date();
                j.apply(o, n)
            }

            function k() {
                h = c
            }

            if (i && !h) {
                l()
            }
            h && clearTimeout(h);
            if (i === c && m > e) {
                l()
            } else {
                if (f !== true) {
                    h = setTimeout(i ? k : l, i === c ? e - m : e)
                }
            }
        }

        if ($.guid) {
            g.guid = j.guid = j.guid || $.guid++
        }
        return g
    };
    $.debounce = function (d, e, f) {
        return f === c ? a(d, e, false) : a(d, f, e !== false)
    }
})(this);

/*!
 * https://github.com/es-shims/es5-shim
 * @license es5-shim Copyright 2009-2014 by contributors, MIT License
 * see https://github.com/es-shims/es5-shim/blob/master/LICENSE
 */
;(function (root, factory) {
    if (typeof define === "function" && define.amd) {
        define(factory)
    } else if (typeof exports === "object") {
        module.exports = factory()
    } else {
        root.returnExports = factory()
    }
})(this, function () {
    function Empty() {
    }

    if (!Function.prototype.bind) {
        Function.prototype.bind = function bind(that) {
            var target = this;
            if (typeof target != "function") {
                throw new TypeError("Function.prototype.bind called on incompatible " + target)
            }
            var args = _Array_slice_.call(arguments, 1);
            var binder = function () {
                if (this instanceof bound) {
                    var result = target.apply(this, args.concat(_Array_slice_.call(arguments)));
                    if (Object(result) === result) {
                        return result
                    }
                    return this
                } else {
                    return target.apply(that, args.concat(_Array_slice_.call(arguments)))
                }
            };
            var boundLength = Math.max(0, target.length - args.length);
            var boundArgs = [];
            for (var i = 0; i < boundLength; i++) {
                boundArgs.push("$" + i)
            }
            var bound = Function("binder", "return function(" + boundArgs.join(",") + "){return binder.apply(this,arguments)}")(binder);
            if (target.prototype) {
                Empty.prototype = target.prototype;
                bound.prototype = new Empty;
                Empty.prototype = null
            }
            return bound
        }
    }
    var call = Function.prototype.call;
    var prototypeOfArray = Array.prototype;
    var prototypeOfObject = Object.prototype;
    var _Array_slice_ = prototypeOfArray.slice;
    var _toString = call.bind(prototypeOfObject.toString);
    var owns = call.bind(prototypeOfObject.hasOwnProperty);
    var defineGetter;
    var defineSetter;
    var lookupGetter;
    var lookupSetter;
    var supportsAccessors;
    if (supportsAccessors = owns(prototypeOfObject, "__defineGetter__")) {
        defineGetter = call.bind(prototypeOfObject.__defineGetter__);
        defineSetter = call.bind(prototypeOfObject.__defineSetter__);
        lookupGetter = call.bind(prototypeOfObject.__lookupGetter__);
        lookupSetter = call.bind(prototypeOfObject.__lookupSetter__)
    }
    if ([1, 2].splice(0).length != 2) {
        var array_splice = Array.prototype.splice;
        var array_push = Array.prototype.push;
        var array_unshift = Array.prototype.unshift;
        if (function () {
                function makeArray(l) {
                    var a = [];
                    while (l--) {
                        a.unshift(l)
                    }
                    return a
                }

                var array = [], lengthBefore;
                array.splice.bind(array, 0, 0).apply(null, makeArray(20));
                array.splice.bind(array, 0, 0).apply(null, makeArray(26));
                lengthBefore = array.length;
                array.splice(5, 0, "XXX");
                if (lengthBefore + 1 == array.length) {
                    return true
                }
            }()) {
            Array.prototype.splice = function (start, deleteCount) {
                if (!arguments.length) {
                    return []
                } else {
                    return array_splice.apply(this, [start === void 0 ? 0 : start, deleteCount === void 0 ? this.length - start : deleteCount].concat(_Array_slice_.call(arguments, 2)))
                }
            }
        } else {
            Array.prototype.splice = function (start, deleteCount) {
                var result, args = _Array_slice_.call(arguments, 2), addElementsCount = args.length;
                if (!arguments.length) {
                    return []
                }
                if (start === void 0) {
                    start = 0
                }
                if (deleteCount === void 0) {
                    deleteCount = this.length - start
                }
                if (addElementsCount > 0) {
                    if (deleteCount <= 0) {
                        if (start == this.length) {
                            array_push.apply(this, args);
                            return []
                        }
                        if (start == 0) {
                            array_unshift.apply(this, args);
                            return []
                        }
                    }
                    result = _Array_slice_.call(this, start, start + deleteCount);
                    args.push.apply(args, _Array_slice_.call(this, start + deleteCount, this.length));
                    args.unshift.apply(args, _Array_slice_.call(this, 0, start));
                    args.unshift(0, this.length);
                    array_splice.apply(this, args);
                    return result
                }
                return array_splice.call(this, start, deleteCount)
            }
        }
    }
    if ([].unshift(0) != 1) {
        var array_unshift = Array.prototype.unshift;
        Array.prototype.unshift = function () {
            array_unshift.apply(this, arguments);
            return this.length
        }
    }
    if (!Array.isArray) {
        Array.isArray = function isArray(obj) {
            return _toString(obj) == "[object Array]"
        }
    }
    var boxedString = Object("a");
    var splitString = boxedString[0] != "a" || !(0 in boxedString);
    var properlyBoxesContext = function properlyBoxed(method) {
        var properlyBoxes = true;
        if (method) {
            method.call("foo", function (item, index, context) {
                if (typeof context !== "object") {
                    properlyBoxes = false
                }
            })
        }
        return !!method && properlyBoxes
    };
    if (!Array.prototype.forEach || !properlyBoxesContext(Array.prototype.forEach)) {
        Array.prototype.forEach = function forEach(fun) {
            var object = toObject(this), self = splitString && _toString(this) == "[object String]" ? this.split("") : object, thisp = arguments[1], i = -1, length = self.length >>> 0;
            if (_toString(fun) != "[object Function]") {
                throw new TypeError
            }
            while (++i < length) {
                if (i in self) {
                    fun.call(thisp, self[i], i, object)
                }
            }
        }
    }
    if (!Array.prototype.map || !properlyBoxesContext(Array.prototype.map)) {
        Array.prototype.map = function map(fun) {
            var object = toObject(this), self = splitString && _toString(this) == "[object String]" ? this.split("") : object, length = self.length >>> 0, result = Array(length), thisp = arguments[1];
            if (_toString(fun) != "[object Function]") {
                throw new TypeError(fun + " is not a function")
            }
            for (var i = 0; i < length; i++) {
                if (i in self)result[i] = fun.call(thisp, self[i], i, object)
            }
            return result
        }
    }
    if (!Array.prototype.filter || !properlyBoxesContext(Array.prototype.filter)) {
        Array.prototype.filter = function filter(fun) {
            var object = toObject(this), self = splitString && _toString(this) == "[object String]" ? this.split("") : object, length = self.length >>> 0, result = [], value, thisp = arguments[1];
            if (_toString(fun) != "[object Function]") {
                throw new TypeError(fun + " is not a function")
            }
            for (var i = 0; i < length; i++) {
                if (i in self) {
                    value = self[i];
                    if (fun.call(thisp, value, i, object)) {
                        result.push(value)
                    }
                }
            }
            return result
        }
    }
    if (!Array.prototype.every || !properlyBoxesContext(Array.prototype.every)) {
        Array.prototype.every = function every(fun) {
            var object = toObject(this), self = splitString && _toString(this) == "[object String]" ? this.split("") : object, length = self.length >>> 0, thisp = arguments[1];
            if (_toString(fun) != "[object Function]") {
                throw new TypeError(fun + " is not a function")
            }
            for (var i = 0; i < length; i++) {
                if (i in self && !fun.call(thisp, self[i], i, object)) {
                    return false
                }
            }
            return true
        }
    }
    if (!Array.prototype.some || !properlyBoxesContext(Array.prototype.some)) {
        Array.prototype.some = function some(fun) {
            var object = toObject(this), self = splitString && _toString(this) == "[object String]" ? this.split("") : object, length = self.length >>> 0, thisp = arguments[1];
            if (_toString(fun) != "[object Function]") {
                throw new TypeError(fun + " is not a function")
            }
            for (var i = 0; i < length; i++) {
                if (i in self && fun.call(thisp, self[i], i, object)) {
                    return true
                }
            }
            return false
        }
    }
    if (!Array.prototype.reduce) {
        Array.prototype.reduce = function reduce(fun) {
            var object = toObject(this), self = splitString && _toString(this) == "[object String]" ? this.split("") : object, length = self.length >>> 0;
            if (_toString(fun) != "[object Function]") {
                throw new TypeError(fun + " is not a function")
            }
            if (!length && arguments.length == 1) {
                throw new TypeError("reduce of empty array with no initial value")
            }
            var i = 0;
            var result;
            if (arguments.length >= 2) {
                result = arguments[1]
            } else {
                do {
                    if (i in self) {
                        result = self[i++];
                        break
                    }
                    if (++i >= length) {
                        throw new TypeError("reduce of empty array with no initial value")
                    }
                } while (true)
            }
            for (; i < length; i++) {
                if (i in self) {
                    result = fun.call(void 0, result, self[i], i, object)
                }
            }
            return result
        }
    }
    if (!Array.prototype.reduceRight) {
        Array.prototype.reduceRight = function reduceRight(fun) {
            var object = toObject(this), self = splitString && _toString(this) == "[object String]" ? this.split("") : object, length = self.length >>> 0;
            if (_toString(fun) != "[object Function]") {
                throw new TypeError(fun + " is not a function")
            }
            if (!length && arguments.length == 1) {
                throw new TypeError("reduceRight of empty array with no initial value")
            }
            var result, i = length - 1;
            if (arguments.length >= 2) {
                result = arguments[1]
            } else {
                do {
                    if (i in self) {
                        result = self[i--];
                        break
                    }
                    if (--i < 0) {
                        throw new TypeError("reduceRight of empty array with no initial value")
                    }
                } while (true)
            }
            if (i < 0) {
                return result
            }
            do {
                if (i in this) {
                    result = fun.call(void 0, result, self[i], i, object)
                }
            } while (i--);
            return result
        }
    }
    if (!Array.prototype.indexOf || [0, 1].indexOf(1, 2) != -1) {
        Array.prototype.indexOf = function indexOf(sought) {
            var self = splitString && _toString(this) == "[object String]" ? this.split("") : toObject(this), length = self.length >>> 0;
            if (!length) {
                return -1
            }
            var i = 0;
            if (arguments.length > 1) {
                i = toInteger(arguments[1])
            }
            i = i >= 0 ? i : Math.max(0, length + i);
            for (; i < length; i++) {
                if (i in self && self[i] === sought) {
                    return i
                }
            }
            return -1
        }
    }
    if (!Array.prototype.lastIndexOf || [0, 1].lastIndexOf(0, -3) != -1) {
        Array.prototype.lastIndexOf = function lastIndexOf(sought) {
            var self = splitString && _toString(this) == "[object String]" ? this.split("") : toObject(this), length = self.length >>> 0;
            if (!length) {
                return -1
            }
            var i = length - 1;
            if (arguments.length > 1) {
                i = Math.min(i, toInteger(arguments[1]))
            }
            i = i >= 0 ? i : length - Math.abs(i);
            for (; i >= 0; i--) {
                if (i in self && sought === self[i]) {
                    return i
                }
            }
            return -1
        }
    }
    if (!Object.keys) {
        var hasDontEnumBug = true, hasProtoEnumBug = function () {
        }.propertyIsEnumerable("prototype"), dontEnums = ["toString", "toLocaleString", "valueOf", "hasOwnProperty", "isPrototypeOf", "propertyIsEnumerable", "constructor"], dontEnumsLength = dontEnums.length;
        for (var key in{toString: null}) {
            hasDontEnumBug = false
        }
        Object.keys = function keys(object) {
            var isFunction = _toString(object) === "[object Function]", isObject = object !== null && typeof object === "object";
            if (!isObject && !isFunction) {
                throw new TypeError("Object.keys called on a non-object")
            }
            var keys = [], skipProto = hasProtoEnumBug && isFunction;
            for (var name in object) {
                if (!(skipProto && name === "prototype") && owns(object, name)) {
                    keys.push(name)
                }
            }
            if (hasDontEnumBug) {
                var ctor = object.constructor, skipConstructor = ctor && ctor.prototype === object;
                for (var i = 0; i < dontEnumsLength; i++) {
                    var dontEnum = dontEnums[i];
                    if (!(skipConstructor && dontEnum === "constructor") && owns(object, dontEnum)) {
                        keys.push(dontEnum)
                    }
                }
            }
            return keys
        }
    }
    var negativeDate = -621987552e5, negativeYearString = "-000001";
    if (!Date.prototype.toISOString || new Date(negativeDate).toISOString().indexOf(negativeYearString) === -1) {
        Date.prototype.toISOString = function toISOString() {
            var result, length, value, year, month;
            if (!isFinite(this)) {
                throw new RangeError("Date.prototype.toISOString called on non-finite value.")
            }
            year = this.getUTCFullYear();
            month = this.getUTCMonth();
            year += Math.floor(month / 12);
            month = (month % 12 + 12) % 12;
            result = [month + 1, this.getUTCDate(), this.getUTCHours(), this.getUTCMinutes(), this.getUTCSeconds()];
            year = (year < 0 ? "-" : year > 9999 ? "+" : "") + ("00000" + Math.abs(year)).slice(0 <= year && year <= 9999 ? -4 : -6);
            length = result.length;
            while (length--) {
                value = result[length];
                if (value < 10) {
                    result[length] = "0" + value
                }
            }
            return year + "-" + result.slice(0, 2).join("-") + "T" + result.slice(2).join(":") + "." + ("000" + this.getUTCMilliseconds()).slice(-3) + "Z"
        }
    }
    var dateToJSONIsSupported = false;
    try {
        dateToJSONIsSupported = Date.prototype.toJSON && new Date(NaN).toJSON() === null && new Date(negativeDate).toJSON().indexOf(negativeYearString) !== -1 && Date.prototype.toJSON.call({
                toISOString: function () {
                    return true
                }
            })
    } catch (e) {
    }
    if (!dateToJSONIsSupported) {
        Date.prototype.toJSON = function toJSON(key) {
            var o = Object(this), tv = toPrimitive(o), toISO;
            if (typeof tv === "number" && !isFinite(tv)) {
                return null
            }
            toISO = o.toISOString;
            if (typeof toISO != "function") {
                throw new TypeError("toISOString property is not callable")
            }
            return toISO.call(o)
        }
    }
    if (!Date.parse || "Date.parse is buggy") {
        Date = function (NativeDate) {
            function Date(Y, M, D, h, m, s, ms) {
                var length = arguments.length;
                if (this instanceof NativeDate) {
                    var date = length == 1 && String(Y) === Y ? new NativeDate(Date.parse(Y)) : length >= 7 ? new NativeDate(Y, M, D, h, m, s, ms) : length >= 6 ? new NativeDate(Y, M, D, h, m, s) : length >= 5 ? new NativeDate(Y, M, D, h, m) : length >= 4 ? new NativeDate(Y, M, D, h) : length >= 3 ? new NativeDate(Y, M, D) : length >= 2 ? new NativeDate(Y, M) : length >= 1 ? new NativeDate(Y) : new NativeDate;
                    date.constructor = Date;
                    return date
                }
                return NativeDate.apply(this, arguments)
            }

            var isoDateExpression = new RegExp("^" + "(\\d{4}|[+-]\\d{6})" + "(?:-(\\d{2})" + "(?:-(\\d{2})" + "(?:" + "T(\\d{2})" + ":(\\d{2})" + "(?:" + ":(\\d{2})" + "(?:(\\.\\d{1,}))?" + ")?" + "(" + "Z|" + "(?:" + "([-+])" + "(\\d{2})" + ":(\\d{2})" + ")" + ")?)?)?)?" + "$");
            var months = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365];

            function dayFromMonth(year, month) {
                var t = month > 1 ? 1 : 0;
                return months[month] + Math.floor((year - 1969 + t) / 4) - Math.floor((year - 1901 + t) / 100) + Math.floor((year - 1601 + t) / 400) + 365 * (year - 1970)
            }

            function toUTC(t) {
                return Number(new NativeDate(1970, 0, 1, 0, 0, 0, t))
            }

            for (var key in NativeDate) {
                Date[key] = NativeDate[key]
            }
            Date.now = NativeDate.now;
            Date.UTC = NativeDate.UTC;
            Date.prototype = NativeDate.prototype;
            Date.prototype.constructor = Date;
            Date.parse = function parse(string) {
                var match = isoDateExpression.exec(string);
                if (match) {
                    var year = Number(match[1]), month = Number(match[2] || 1) - 1, day = Number(match[3] || 1) - 1, hour = Number(match[4] || 0), minute = Number(match[5] || 0), second = Number(match[6] || 0), millisecond = Math.floor(Number(match[7] || 0) * 1e3), isLocalTime = Boolean(match[4] && !match[8]), signOffset = match[9] === "-" ? 1 : -1, hourOffset = Number(match[10] || 0), minuteOffset = Number(match[11] || 0), result;
                    if (hour < (minute > 0 || second > 0 || millisecond > 0 ? 24 : 25) && minute < 60 && second < 60 && millisecond < 1e3 && month > -1 && month < 12 && hourOffset < 24 && minuteOffset < 60 && day > -1 && day < dayFromMonth(year, month + 1) - dayFromMonth(year, month)) {
                        result = ((dayFromMonth(year, month) + day) * 24 + hour + hourOffset * signOffset) * 60;
                        result = ((result + minute + minuteOffset * signOffset) * 60 + second) * 1e3 + millisecond;
                        if (isLocalTime) {
                            result = toUTC(result)
                        }
                        if (-864e13 <= result && result <= 864e13) {
                            return result
                        }
                    }
                    return NaN
                }
                return NativeDate.parse.apply(this, arguments)
            };
            return Date
        }(Date)
    }
    if (!Date.now) {
        Date.now = function now() {
            return (new Date).getTime()
        }
    }
    if (!Number.prototype.toFixed || 8e-5.toFixed(3) !== "0.000" || .9.toFixed(0) === "0" || 1.255.toFixed(2) !== "1.25" || 0xde0b6b3a7640080.toFixed(0) !== "1000000000000000128") {
        (function () {
            var base, size, data, i;
            base = 1e7;
            size = 6;
            data = [0, 0, 0, 0, 0, 0];
            function multiply(n, c) {
                var i = -1;
                while (++i < size) {
                    c += n * data[i];
                    data[i] = c % base;
                    c = Math.floor(c / base)
                }
            }

            function divide(n) {
                var i = size, c = 0;
                while (--i >= 0) {
                    c += data[i];
                    data[i] = Math.floor(c / n);
                    c = c % n * base
                }
            }

            function toString() {
                var i = size;
                var s = "";
                while (--i >= 0) {
                    if (s !== "" || i === 0 || data[i] !== 0) {
                        var t = String(data[i]);
                        if (s === "") {
                            s = t
                        } else {
                            s += "0000000".slice(0, 7 - t.length) + t
                        }
                    }
                }
                return s
            }

            function pow(x, n, acc) {
                return n === 0 ? acc : n % 2 === 1 ? pow(x, n - 1, acc * x) : pow(x * x, n / 2, acc)
            }

            function log(x) {
                var n = 0;
                while (x >= 4096) {
                    n += 12;
                    x /= 4096
                }
                while (x >= 2) {
                    n += 1;
                    x /= 2
                }
                return n
            }

            Number.prototype.toFixed = function (fractionDigits) {
                var f, x, s, m, e, z, j, k;
                f = Number(fractionDigits);
                f = f !== f ? 0 : Math.floor(f);
                if (f < 0 || f > 20) {
                    throw new RangeError("Number.toFixed called with invalid number of decimals")
                }
                x = Number(this);
                if (x !== x) {
                    return "NaN"
                }
                if (x <= -1e21 || x >= 1e21) {
                    return String(x)
                }
                s = "";
                if (x < 0) {
                    s = "-";
                    x = -x
                }
                m = "0";
                if (x > 1e-21) {
                    e = log(x * pow(2, 69, 1)) - 69;
                    z = e < 0 ? x * pow(2, -e, 1) : x / pow(2, e, 1);
                    z *= 4503599627370496;
                    e = 52 - e;
                    if (e > 0) {
                        multiply(0, z);
                        j = f;
                        while (j >= 7) {
                            multiply(1e7, 0);
                            j -= 7
                        }
                        multiply(pow(10, j, 1), 0);
                        j = e - 1;
                        while (j >= 23) {
                            divide(1 << 23);
                            j -= 23
                        }
                        divide(1 << j);
                        multiply(1, 1);
                        divide(2);
                        m = toString()
                    } else {
                        multiply(0, z);
                        multiply(1 << -e, 0);
                        m = toString() + "0.00000000000000000000".slice(2, 2 + f)
                    }
                }
                if (f > 0) {
                    k = m.length;
                    if (k <= f) {
                        m = s + "0.0000000000000000000".slice(0, f - k + 2) + m
                    } else {
                        m = s + m.slice(0, k - f) + "." + m.slice(k - f)
                    }
                } else {
                    m = s + m
                }
                return m
            }
        })()
    }
    var string_split = String.prototype.split;
    if ("ab".split(/(?:ab)*/).length !== 2 || ".".split(/(.?)(.?)/).length !== 4 || "tesst".split(/(s)*/)[1] === "t" || "".split(/.?/).length || ".".split(/()()/).length > 1) {
        (function () {
            var compliantExecNpcg = /()??/.exec("")[1] === void 0;
            String.prototype.split = function (separator, limit) {
                var string = this;
                if (separator === void 0 && limit === 0)return [];
                if (Object.prototype.toString.call(separator) !== "[object RegExp]") {
                    return string_split.apply(this, arguments)
                }
                var output = [], flags = (separator.ignoreCase ? "i" : "") + (separator.multiline ? "m" : "") + (separator.extended ? "x" : "") + (separator.sticky ? "y" : ""), lastLastIndex = 0, separator = new RegExp(separator.source, flags + "g"), separator2, match, lastIndex, lastLength;
                string += "";
                if (!compliantExecNpcg) {
                    separator2 = new RegExp("^" + separator.source + "$(?!\\s)", flags)
                }
                limit = limit === void 0 ? -1 >>> 0 : limit >>> 0;
                while (match = separator.exec(string)) {
                    lastIndex = match.index + match[0].length;
                    if (lastIndex > lastLastIndex) {
                        output.push(string.slice(lastLastIndex, match.index));
                        if (!compliantExecNpcg && match.length > 1) {
                            match[0].replace(separator2, function () {
                                for (var i = 1; i < arguments.length - 2; i++) {
                                    if (arguments[i] === void 0) {
                                        match[i] = void 0
                                    }
                                }
                            })
                        }
                        if (match.length > 1 && match.index < string.length) {
                            Array.prototype.push.apply(output, match.slice(1))
                        }
                        lastLength = match[0].length;
                        lastLastIndex = lastIndex;
                        if (output.length >= limit) {
                            break
                        }
                    }
                    if (separator.lastIndex === match.index) {
                        separator.lastIndex++
                    }
                }
                if (lastLastIndex === string.length) {
                    if (lastLength || !separator.test("")) {
                        output.push("")
                    }
                } else {
                    output.push(string.slice(lastLastIndex))
                }
                return output.length > limit ? output.slice(0, limit) : output
            }
        })()
    } else if ("0".split(void 0, 0).length) {
        String.prototype.split = function (separator, limit) {
            if (separator === void 0 && limit === 0)return [];
            return string_split.apply(this, arguments)
        }
    }
    if ("".substr && "0b".substr(-1) !== "b") {
        var string_substr = String.prototype.substr;
        String.prototype.substr = function (start, length) {
            return string_substr.call(this, start < 0 ? (start = this.length + start) < 0 ? 0 : start : start, length)
        }
    }
    var ws = "	\n\f\r \xa0\u1680\u180e\u2000\u2001\u2002\u2003" + "\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028" + "\u2029\ufeff";
    if (!String.prototype.trim || ws.trim()) {
        ws = "[" + ws + "]";
        var trimBeginRegexp = new RegExp("^" + ws + ws + "*"), trimEndRegexp = new RegExp(ws + ws + "*$");
        String.prototype.trim = function trim() {
            if (this === void 0 || this === null) {
                throw new TypeError("can't convert " + this + " to object")
            }
            return String(this).replace(trimBeginRegexp, "").replace(trimEndRegexp, "")
        }
    }
    if (parseInt(ws + "08") !== 8 || parseInt(ws + "0x16") !== 22) {
        parseInt = function (origParseInt) {
            var hexRegex = /^0[xX]/;
            return function parseIntES5(str, radix) {
                str = String(str).trim();
                if (!+radix) {
                    radix = hexRegex.test(str) ? 16 : 10
                }
                return origParseInt(str, radix)
            }
        }(parseInt)
    }
    function toInteger(n) {
        n = +n;
        if (n !== n) {
            n = 0
        } else if (n !== 0 && n !== 1 / 0 && n !== -(1 / 0)) {
            n = (n > 0 || -1) * Math.floor(Math.abs(n))
        }
        return n
    }

    function isPrimitive(input) {
        var type = typeof input;
        return input === null || type === "undefined" || type === "boolean" || type === "number" || type === "string"
    }

    function toPrimitive(input) {
        var val, valueOf, toString;
        if (isPrimitive(input)) {
            return input
        }
        valueOf = input.valueOf;
        if (typeof valueOf === "function") {
            val = valueOf.call(input);
            if (isPrimitive(val)) {
                return val
            }
        }
        toString = input.toString;
        if (typeof toString === "function") {
            val = toString.call(input);
            if (isPrimitive(val)) {
                return val
            }
        }
        throw new TypeError
    }

    var toObject = function (o) {
        if (o == null) {
            throw new TypeError("can't convert " + o + " to object")
        }
        return Object(o)
    }
});
//# sourceMappingURL=es5-shim.map

/*! Magnific Popup - v0.9.9 - 2013-12-27
 * http://dimsemenov.com/plugins/magnific-popup/
 * Copyright (c) 2013 Dmitry Semenov; */
;(function (e) {
    var t, n, i, o, r, a, s, l = "Close", c = "BeforeClose", d = "AfterClose", u = "BeforeAppend", p = "MarkupParse", f = "Open", m = "Change", g = "mfp", h = "." + g, v = "mfp-ready", C = "mfp-removing", y = "mfp-prevent-close", w = function () {
    }, b = !!window.jQuery, I = e(window), x = function (e, n) {
        t.ev.on(g + e + h, n)
    }, k = function (t, n, i, o) {
        var r = document.createElement("div");
        return r.className = "mfp-" + t, i && (r.innerHTML = i), o ? n && n.appendChild(r) : (r = e(r), n && r.appendTo(n)), r
    }, T = function (n, i) {
        t.ev.triggerHandler(g + n, i), t.st.callbacks && (n = n.charAt(0).toLowerCase() + n.slice(1), t.st.callbacks[n] && t.st.callbacks[n].apply(t, e.isArray(i) ? i : [i]))
    }, E = function (n) {
        return n === s && t.currTemplate.closeBtn || (t.currTemplate.closeBtn = e(t.st.closeMarkup.replace("%title%", t.st.tClose)), s = n), t.currTemplate.closeBtn
    }, _ = function () {
        e.magnificPopup.instance || (t = new w, t.init(), e.magnificPopup.instance = t)
    }, S = function () {
        var e = document.createElement("p").style, t = ["ms", "O", "Moz", "Webkit"];
        if (void 0 !== e.transition)return !0;
        for (; t.length;)if (t.pop() + "Transition" in e)return !0;
        return !1
    };
    w.prototype = {
        constructor: w, init: function () {
            var n = navigator.appVersion;
            t.isIE7 = -1 !== n.indexOf("MSIE 7."), t.isIE8 = -1 !== n.indexOf("MSIE 8."), t.isLowIE = t.isIE7 || t.isIE8, t.isAndroid = /android/gi.test(n), t.isIOS = /iphone|ipad|ipod/gi.test(n), t.supportsTransition = S(), t.probablyMobile = t.isAndroid || t.isIOS || /(Opera Mini)|Kindle|webOS|BlackBerry|(Opera Mobi)|(Windows Phone)|IEMobile/i.test(navigator.userAgent), o = e(document), t.popupsCache = {}
        }, open: function (n) {
            i || (i = e(document.body));
            var r;
            if (n.isObj === !1) {
                t.items = n.items.toArray(), t.index = 0;
                var s, l = n.items;
                for (r = 0; l.length > r; r++)if (s = l[r], s.parsed && (s = s.el[0]), s === n.el[0]) {
                    t.index = r;
                    break
                }
            } else t.items = e.isArray(n.items) ? n.items : [n.items], t.index = n.index || 0;
            if (t.isOpen)return t.updateItemHTML(), void 0;
            t.types = [], a = "", t.ev = n.mainEl && n.mainEl.length ? n.mainEl.eq(0) : o, n.key ? (t.popupsCache[n.key] || (t.popupsCache[n.key] = {}), t.currTemplate = t.popupsCache[n.key]) : t.currTemplate = {}, t.st = e.extend(!0, {}, e.magnificPopup.defaults, n), t.fixedContentPos = "auto" === t.st.fixedContentPos ? !t.probablyMobile : t.st.fixedContentPos, t.st.modal && (t.st.closeOnContentClick = !1, t.st.closeOnBgClick = !1, t.st.showCloseBtn = !1, t.st.enableEscapeKey = !1), t.bgOverlay || (t.bgOverlay = k("bg").on("click" + h, function () {
                t.close()
            }), t.wrap = k("wrap").attr("tabindex", -1).on("click" + h, function (e) {
                t._checkIfClose(e.target) && t.close()
            }), t.container = k("container", t.wrap)), t.contentContainer = k("content"), t.st.preloader && (t.preloader = k("preloader", t.container, t.st.tLoading));
            var c = e.magnificPopup.modules;
            for (r = 0; c.length > r; r++) {
                var d = c[r];
                d = d.charAt(0).toUpperCase() + d.slice(1), t["init" + d].call(t)
            }
            T("BeforeOpen"), t.st.showCloseBtn && (t.st.closeBtnInside ? (x(p, function (e, t, n, i) {
                n.close_replaceWith = E(i.type)
            }), a += " mfp-close-btn-in") : t.wrap.append(E())), t.st.alignTop && (a += " mfp-align-top"), t.fixedContentPos ? t.wrap.css({
                overflow: t.st.overflowY,
                overflowX: "hidden",
                overflowY: t.st.overflowY
            }) : t.wrap.css({
                top: I.scrollTop(),
                position: "absolute"
            }), (t.st.fixedBgPos === !1 || "auto" === t.st.fixedBgPos && !t.fixedContentPos) && t.bgOverlay.css({
                height: o.height(),
                position: "absolute"
            }), t.st.enableEscapeKey && o.on("keyup" + h, function (e) {
                27 === e.keyCode && t.close()
            }), I.on("resize" + h, function () {
                t.updateSize()
            }), t.st.closeOnContentClick || (a += " mfp-auto-cursor"), a && t.wrap.addClass(a);
            var u = t.wH = I.height(), m = {};
            if (t.fixedContentPos && t._hasScrollBar(u)) {
                var g = t._getScrollbarSize();
                g && (m.marginRight = g)
            }
            t.fixedContentPos && (t.isIE7 ? e("body, html").css("overflow", "hidden") : m.overflow = "hidden");
            var C = t.st.mainClass;
            return t.isIE7 && (C += " mfp-ie7"), C && t._addClassToMFP(C), t.updateItemHTML(), T("BuildControls"), e("html").css(m), t.bgOverlay.add(t.wrap).prependTo(t.st.prependTo || i), t._lastFocusedEl = document.activeElement, setTimeout(function () {
                t.content ? (t._addClassToMFP(v), t._setFocus()) : t.bgOverlay.addClass(v), o.on("focusin" + h, t._onFocusIn)
            }, 16), t.isOpen = !0, t.updateSize(u), T(f), n
        }, close: function () {
            t.isOpen && (T(c), t.isOpen = !1, t.st.removalDelay && !t.isLowIE && t.supportsTransition ? (t._addClassToMFP(C), setTimeout(function () {
                t._close()
            }, t.st.removalDelay)) : t._close())
        }, _close: function () {
            T(l);
            var n = C + " " + v + " ";
            if (t.bgOverlay.detach(), t.wrap.detach(), t.container.empty(), t.st.mainClass && (n += t.st.mainClass + " "), t._removeClassFromMFP(n), t.fixedContentPos) {
                var i = {marginRight: ""};
                t.isIE7 ? e("body, html").css("overflow", "") : i.overflow = "", e("html").css(i)
            }
            o.off("keyup" + h + " focusin" + h), t.ev.off(h), t.wrap.attr("class", "mfp-wrap").removeAttr("style"), t.bgOverlay.attr("class", "mfp-bg"), t.container.attr("class", "mfp-container"), !t.st.showCloseBtn || t.st.closeBtnInside && t.currTemplate[t.currItem.type] !== !0 || t.currTemplate.closeBtn && t.currTemplate.closeBtn.detach(), t._lastFocusedEl && e(t._lastFocusedEl).focus(), t.currItem = null, t.content = null, t.currTemplate = null, t.prevHeight = 0, T(d)
        }, updateSize: function (e) {
            if (t.isIOS) {
                var n = document.documentElement.clientWidth / window.innerWidth, i = window.innerHeight * n;
                t.wrap.css("height", i), t.wH = i
            } else t.wH = e || I.height();
            t.fixedContentPos || t.wrap.css("height", t.wH), T("Resize")
        }, updateItemHTML: function () {
            var n = t.items[t.index];
            t.contentContainer.detach(), t.content && t.content.detach(), n.parsed || (n = t.parseEl(t.index));
            var i = n.type;
            if (T("BeforeChange", [t.currItem ? t.currItem.type : "", i]), t.currItem = n, !t.currTemplate[i]) {
                var o = t.st[i] ? t.st[i].markup : !1;
                T("FirstMarkupParse", o), t.currTemplate[i] = o ? e(o) : !0
            }
            r && r !== n.type && t.container.removeClass("mfp-" + r + "-holder");
            var a = t["get" + i.charAt(0).toUpperCase() + i.slice(1)](n, t.currTemplate[i]);
            t.appendContent(a, i), n.preloaded = !0, T(m, n), r = n.type, t.container.prepend(t.contentContainer), T("AfterChange")
        }, appendContent: function (e, n) {
            t.content = e, e ? t.st.showCloseBtn && t.st.closeBtnInside && t.currTemplate[n] === !0 ? t.content.find(".mfp-close").length || t.content.append(E()) : t.content = e : t.content = "", T(u), t.container.addClass("mfp-" + n + "-holder"), t.contentContainer.append(t.content)
        }, parseEl: function (n) {
            var i, o = t.items[n];
            if (o.tagName ? o = {el: e(o)} : (i = o.type, o = {data: o, src: o.src}), o.el) {
                for (var r = t.types, a = 0; r.length > a; a++)if (o.el.hasClass("mfp-" + r[a])) {
                    i = r[a];
                    break
                }
                o.src = o.el.attr("data-mfp-src"), o.src || (o.src = o.el.attr("href"))
            }
            return o.type = i || t.st.type || "inline", o.index = n, o.parsed = !0, t.items[n] = o, T("ElementParse", o), t.items[n]
        }, addGroup: function (e, n) {
            var i = function (i) {
                i.mfpEl = this, t._openClick(i, e, n)
            };
            n || (n = {});
            var o = "click.magnificPopup";
            n.mainEl = e, n.items ? (n.isObj = !0, e.off(o).on(o, i)) : (n.isObj = !1, n.delegate ? e.off(o).on(o, n.delegate, i) : (n.items = e, e.off(o).on(o, i)))
        }, _openClick: function (n, i, o) {
            var r = void 0 !== o.midClick ? o.midClick : e.magnificPopup.defaults.midClick;
            if (r || 2 !== n.which && !n.ctrlKey && !n.metaKey) {
                var a = void 0 !== o.disableOn ? o.disableOn : e.magnificPopup.defaults.disableOn;
                if (a)if (e.isFunction(a)) {
                    if (!a.call(t))return !0
                } else if (a > I.width())return !0;
                n.type && (n.preventDefault(), t.isOpen && n.stopPropagation()), o.el = e(n.mfpEl), o.delegate && (o.items = i.find(o.delegate)), t.open(o)
            }
        }, updateStatus: function (e, i) {
            if (t.preloader) {
                n !== e && t.container.removeClass("mfp-s-" + n), i || "loading" !== e || (i = t.st.tLoading);
                var o = {status: e, text: i};
                T("UpdateStatus", o), e = o.status, i = o.text, t.preloader.html(i), t.preloader.find("a").on("click", function (e) {
                    e.stopImmediatePropagation()
                }), t.container.addClass("mfp-s-" + e), n = e
            }
        }, _checkIfClose: function (n) {
            if (!e(n).hasClass(y)) {
                var i = t.st.closeOnContentClick, o = t.st.closeOnBgClick;
                if (i && o)return !0;
                if (!t.content || e(n).hasClass("mfp-close") || t.preloader && n === t.preloader[0])return !0;
                if (n === t.content[0] || e.contains(t.content[0], n)) {
                    if (i)return !0
                } else if (o && e.contains(document, n))return !0;
                return !1
            }
        }, _addClassToMFP: function (e) {
            t.bgOverlay.addClass(e), t.wrap.addClass(e)
        }, _removeClassFromMFP: function (e) {
            this.bgOverlay.removeClass(e), t.wrap.removeClass(e)
        }, _hasScrollBar: function (e) {
            return (t.isIE7 ? o.height() : document.body.scrollHeight) > (e || I.height())
        }, _setFocus: function () {
            (t.st.focus ? t.content.find(t.st.focus).eq(0) : t.wrap).focus()
        }, _onFocusIn: function (n) {
            return n.target === t.wrap[0] || e.contains(t.wrap[0], n.target) ? void 0 : (t._setFocus(), !1)
        }, _parseMarkup: function (t, n, i) {
            var o;
            i.data && (n = e.extend(i.data, n)), T(p, [t, n, i]), e.each(n, function (e, n) {
                if (void 0 === n || n === !1)return !0;
                if (o = e.split("_"), o.length > 1) {
                    var i = t.find(h + "-" + o[0]);
                    if (i.length > 0) {
                        var r = o[1];
                        "replaceWith" === r ? i[0] !== n[0] && i.replaceWith(n) : "img" === r ? i.is("img") ? i.attr("src", n) : i.replaceWith('<img src="' + n + '" class="' + i.attr("class") + '" />') : i.attr(o[1], n)
                    }
                } else t.find(h + "-" + e).html(n)
            })
        }, _getScrollbarSize: function () {
            if (void 0 === t.scrollbarSize) {
                var e = document.createElement("div");
                e.id = "mfp-sbm", e.style.cssText = "width: 99px; height: 99px; overflow: scroll; position: absolute; top: -9999px;", document.body.appendChild(e), t.scrollbarSize = e.offsetWidth - e.clientWidth, document.body.removeChild(e)
            }
            return t.scrollbarSize
        }
    }, e.magnificPopup = {
        instance: null,
        proto: w.prototype,
        modules: [],
        open: function (t, n) {
            return _(), t = t ? e.extend(!0, {}, t) : {}, t.isObj = !0, t.index = n || 0, this.instance.open(t)
        },
        close: function () {
            return e.magnificPopup.instance && e.magnificPopup.instance.close()
        },
        registerModule: function (t, n) {
            n.options && (e.magnificPopup.defaults[t] = n.options), e.extend(this.proto, n.proto), this.modules.push(t)
        },
        defaults: {
            disableOn: 0,
            key: null,
            midClick: !1,
            mainClass: "",
            preloader: !0,
            focus: "",
            closeOnContentClick: !1,
            closeOnBgClick: !0,
            closeBtnInside: !0,
            showCloseBtn: !0,
            enableEscapeKey: !0,
            modal: !1,
            alignTop: !1,
            removalDelay: 0,
            prependTo: null,
            fixedContentPos: "auto",
            fixedBgPos: "auto",
            overflowY: "auto",
            closeMarkup: '<button title="%title%" type="button" class="mfp-close">&times;</button>',
            tClose: "Close (Esc)",
            tLoading: "Loading..."
        }
    }, e.fn.magnificPopup = function (n) {
        _();
        var i = e(this);
        if ("string" == typeof n)if ("open" === n) {
            var o, r = b ? i.data("magnificPopup") : i[0].magnificPopup, a = parseInt(arguments[1], 10) || 0;
            r.items ? o = r.items[a] : (o = i, r.delegate && (o = o.find(r.delegate)), o = o.eq(a)), t._openClick({mfpEl: o}, i, r)
        } else t.isOpen && t[n].apply(t, Array.prototype.slice.call(arguments, 1)); else n = e.extend(!0, {}, n), b ? i.data("magnificPopup", n) : i[0].magnificPopup = n, t.addGroup(i, n);
        return i
    };
    var P, O, z, M = "inline", B = function () {
        z && (O.after(z.addClass(P)).detach(), z = null)
    };
    e.magnificPopup.registerModule(M, {
        options: {hiddenClass: "hide", markup: "", tNotFound: "Content not found"},
        proto: {
            initInline: function () {
                t.types.push(M), x(l + "." + M, function () {
                    B()
                })
            }, getInline: function (n, i) {
                if (B(), n.src) {
                    var o = t.st.inline, r = e(n.src);
                    if (r.length) {
                        var a = r[0].parentNode;
                        a && a.tagName && (O || (P = o.hiddenClass, O = k(P), P = "mfp-" + P), z = r.after(O).detach().removeClass(P)), t.updateStatus("ready")
                    } else t.updateStatus("error", o.tNotFound), r = e("<div>");
                    return n.inlineElement = r, r
                }
                return t.updateStatus("ready"), t._parseMarkup(i, {}, n), i
            }
        }
    });
    var F, H = "ajax", L = function () {
        F && i.removeClass(F)
    }, A = function () {
        L(), t.req && t.req.abort()
    };
    e.magnificPopup.registerModule(H, {
        options: {
            settings: null,
            cursor: "mfp-ajax-cur",
            tError: '<a href="%url%">The content</a> could not be loaded.'
        }, proto: {
            initAjax: function () {
                t.types.push(H), F = t.st.ajax.cursor, x(l + "." + H, A), x("BeforeChange." + H, A)
            }, getAjax: function (n) {
                F && i.addClass(F), t.updateStatus("loading");
                var o = e.extend({
                    url: n.src, success: function (i, o, r) {
                        var a = {data: i, xhr: r};
                        T("ParseAjax", a), t.appendContent(e(a.data), H), n.finished = !0, L(), t._setFocus(), setTimeout(function () {
                            t.wrap.addClass(v)
                        }, 16), t.updateStatus("ready"), T("AjaxContentAdded")
                    }, error: function () {
                        L(), n.finished = n.loadError = !0, t.updateStatus("error", t.st.ajax.tError.replace("%url%", n.src))
                    }
                }, t.st.ajax.settings);
                return t.req = e.ajax(o), ""
            }
        }
    });
    var j, N = function (n) {
        if (n.data && void 0 !== n.data.title)return n.data.title;
        var i = t.st.image.titleSrc;
        if (i) {
            if (e.isFunction(i))return i.call(t, n);
            if (n.el)return n.el.attr(i) || ""
        }
        return ""
    };
    e.magnificPopup.registerModule("image", {
        options: {
            markup: '<div class="mfp-figure"><div class="mfp-close"></div><figure><div class="mfp-img"></div><figcaption><div class="mfp-bottom-bar"><div class="mfp-title"></div><div class="mfp-counter"></div></div></figcaption></figure></div>',
            cursor: "mfp-zoom-out-cur",
            titleSrc: "title",
            verticalFit: !0,
            tError: '<a href="%url%">The image</a> could not be loaded.'
        }, proto: {
            initImage: function () {
                var e = t.st.image, n = ".image";
                t.types.push("image"), x(f + n, function () {
                    "image" === t.currItem.type && e.cursor && i.addClass(e.cursor)
                }), x(l + n, function () {
                    e.cursor && i.removeClass(e.cursor), I.off("resize" + h)
                }), x("Resize" + n, t.resizeImage), t.isLowIE && x("AfterChange", t.resizeImage)
            }, resizeImage: function () {
                var e = t.currItem;
                if (e && e.img && t.st.image.verticalFit) {
                    var n = 0;
                    t.isLowIE && (n = parseInt(e.img.css("padding-top"), 10) + parseInt(e.img.css("padding-bottom"), 10)), e.img.css("max-height", t.wH - n)
                }
            }, _onImageHasSize: function (e) {
                e.img && (e.hasSize = !0, j && clearInterval(j), e.isCheckingImgSize = !1, T("ImageHasSize", e), e.imgHidden && (t.content && t.content.removeClass("mfp-loading"), e.imgHidden = !1))
            }, findImageSize: function (e) {
                var n = 0, i = e.img[0], o = function (r) {
                    j && clearInterval(j), j = setInterval(function () {
                        return i.naturalWidth > 0 ? (t._onImageHasSize(e), void 0) : (n > 200 && clearInterval(j), n++, 3 === n ? o(10) : 40 === n ? o(50) : 100 === n && o(500), void 0)
                    }, r)
                };
                o(1)
            }, getImage: function (n, i) {
                var o = 0, r = function () {
                    n && (n.img[0].complete ? (n.img.off(".mfploader"), n === t.currItem && (t._onImageHasSize(n), t.updateStatus("ready")), n.hasSize = !0, n.loaded = !0, T("ImageLoadComplete")) : (o++, 200 > o ? setTimeout(r, 100) : a()))
                }, a = function () {
                    n && (n.img.off(".mfploader"), n === t.currItem && (t._onImageHasSize(n), t.updateStatus("error", s.tError.replace("%url%", n.src))), n.hasSize = !0, n.loaded = !0, n.loadError = !0)
                }, s = t.st.image, l = i.find(".mfp-img");
                if (l.length) {
                    var c = document.createElement("img");
                    c.className = "mfp-img", n.img = e(c).on("load.mfploader", r).on("error.mfploader", a), c.src = n.src, l.is("img") && (n.img = n.img.clone()), c = n.img[0], c.naturalWidth > 0 ? n.hasSize = !0 : c.width || (n.hasSize = !1)
                }
                return t._parseMarkup(i, {
                    title: N(n),
                    img_replaceWith: n.img
                }, n), t.resizeImage(), n.hasSize ? (j && clearInterval(j), n.loadError ? (i.addClass("mfp-loading"), t.updateStatus("error", s.tError.replace("%url%", n.src))) : (i.removeClass("mfp-loading"), t.updateStatus("ready")), i) : (t.updateStatus("loading"), n.loading = !0, n.hasSize || (n.imgHidden = !0, i.addClass("mfp-loading"), t.findImageSize(n)), i)
            }
        }
    });
    var W, R = function () {
        return void 0 === W && (W = void 0 !== document.createElement("p").style.MozTransform), W
    };
    e.magnificPopup.registerModule("zoom", {
        options: {
            enabled: !1,
            easing: "ease-in-out",
            duration: 300,
            opener: function (e) {
                return e.is("img") ? e : e.find("img")
            }
        }, proto: {
            initZoom: function () {
                var e, n = t.st.zoom, i = ".zoom";
                if (n.enabled && t.supportsTransition) {
                    var o, r, a = n.duration, s = function (e) {
                        var t = e.clone().removeAttr("style").removeAttr("class").addClass("mfp-animated-image"), i = "all " + n.duration / 1e3 + "s " + n.easing, o = {
                            position: "fixed",
                            zIndex: 9999,
                            left: 0,
                            top: 0,
                            "-webkit-backface-visibility": "hidden"
                        }, r = "transition";
                        return o["-webkit-" + r] = o["-moz-" + r] = o["-o-" + r] = o[r] = i, t.css(o), t
                    }, d = function () {
                        t.content.css("visibility", "visible")
                    };
                    x("BuildControls" + i, function () {
                        if (t._allowZoom()) {
                            if (clearTimeout(o), t.content.css("visibility", "hidden"), e = t._getItemToZoom(), !e)return d(), void 0;
                            r = s(e), r.css(t._getOffset()), t.wrap.append(r), o = setTimeout(function () {
                                r.css(t._getOffset(!0)), o = setTimeout(function () {
                                    d(), setTimeout(function () {
                                        r.remove(), e = r = null, T("ZoomAnimationEnded")
                                    }, 16)
                                }, a)
                            }, 16)
                        }
                    }), x(c + i, function () {
                        if (t._allowZoom()) {
                            if (clearTimeout(o), t.st.removalDelay = a, !e) {
                                if (e = t._getItemToZoom(), !e)return;
                                r = s(e)
                            }
                            r.css(t._getOffset(!0)), t.wrap.append(r), t.content.css("visibility", "hidden"), setTimeout(function () {
                                r.css(t._getOffset())
                            }, 16)
                        }
                    }), x(l + i, function () {
                        t._allowZoom() && (d(), r && r.remove(), e = null)
                    })
                }
            }, _allowZoom: function () {
                return "image" === t.currItem.type
            }, _getItemToZoom: function () {
                return t.currItem.hasSize ? t.currItem.img : !1
            }, _getOffset: function (n) {
                var i;
                i = n ? t.currItem.img : t.st.zoom.opener(t.currItem.el || t.currItem);
                var o = i.offset(), r = parseInt(i.css("padding-top"), 10), a = parseInt(i.css("padding-bottom"), 10);
                o.top -= e(window).scrollTop() - r;
                var s = {width: i.width(), height: (b ? i.innerHeight() : i[0].offsetHeight) - a - r};
                return R() ? s["-moz-transform"] = s.transform = "translate(" + o.left + "px," + o.top + "px)" : (s.left = o.left, s.top = o.top), s
            }
        }
    });
    var Z = "iframe", q = "//about:blank", D = function (e) {
        if (t.currTemplate[Z]) {
            var n = t.currTemplate[Z].find("iframe");
            n.length && (e || (n[0].src = q), t.isIE8 && n.css("display", e ? "block" : "none"))
        }
    };
    e.magnificPopup.registerModule(Z, {
        options: {
            markup: '<div class="mfp-iframe-scaler"><div class="mfp-close"></div><iframe class="mfp-iframe" src="//about:blank" frameborder="0" allowfullscreen></iframe></div>',
            srcAction: "iframe_src",
            patterns: {
                youtube: {index: "youtube.com", id: "v=", src: "//www.youtube.com/embed/%id%?autoplay=1"},
                vimeo: {index: "vimeo.com/", id: "/", src: "//player.vimeo.com/video/%id%?autoplay=1"},
                gmaps: {index: "//maps.google.", src: "%id%&output=embed"}
            }
        }, proto: {
            initIframe: function () {
                t.types.push(Z), x("BeforeChange", function (e, t, n) {
                    t !== n && (t === Z ? D() : n === Z && D(!0))
                }), x(l + "." + Z, function () {
                    D()
                })
            }, getIframe: function (n, i) {
                var o = n.src, r = t.st.iframe;
                e.each(r.patterns, function () {
                    return o.indexOf(this.index) > -1 ? (this.id && (o = "string" == typeof this.id ? o.substr(o.lastIndexOf(this.id) + this.id.length, o.length) : this.id.call(this, o)), o = this.src.replace("%id%", o), !1) : void 0
                });
                var a = {};
                return r.srcAction && (a[r.srcAction] = o), t._parseMarkup(i, a, n), t.updateStatus("ready"), i
            }
        }
    });
    var K = function (e) {
        var n = t.items.length;
        return e > n - 1 ? e - n : 0 > e ? n + e : e
    }, Y = function (e, t, n) {
        return e.replace(/%curr%/gi, t + 1).replace(/%total%/gi, n)
    };
    e.magnificPopup.registerModule("gallery", {
        options: {
            enabled: !1,
            arrowMarkup: '<button title="%title%" type="button" class="mfp-arrow mfp-arrow-%dir%"></button>',
            preload: [0, 2],
            navigateByImgClick: !0,
            arrows: !0,
            tPrev: "Previous (Left arrow key)",
            tNext: "Next (Right arrow key)",
            tCounter: "%curr% / %total%"
        }, proto: {
            initGallery: function () {
                var n = t.st.gallery, i = ".mfp-gallery", r = Boolean(e.fn.mfpFastClick);
                return t.direction = !0, n && n.enabled ? (a += " mfp-gallery", x(f + i, function () {
                    n.navigateByImgClick && t.wrap.on("click" + i, ".mfp-img", function () {
                        return t.items.length > 1 ? (t.next(), !1) : void 0
                    }), o.on("keydown" + i, function (e) {
                        37 === e.keyCode ? t.prev() : 39 === e.keyCode && t.next()
                    })
                }), x("UpdateStatus" + i, function (e, n) {
                    n.text && (n.text = Y(n.text, t.currItem.index, t.items.length))
                }), x(p + i, function (e, i, o, r) {
                    var a = t.items.length;
                    o.counter = a > 1 ? Y(n.tCounter, r.index, a) : ""
                }), x("BuildControls" + i, function () {
                    if (t.items.length > 1 && n.arrows && !t.arrowLeft) {
                        var i = n.arrowMarkup, o = t.arrowLeft = e(i.replace(/%title%/gi, n.tPrev).replace(/%dir%/gi, "left")).addClass(y), a = t.arrowRight = e(i.replace(/%title%/gi, n.tNext).replace(/%dir%/gi, "right")).addClass(y), s = r ? "mfpFastClick" : "click";
                        o[s](function () {
                            t.prev()
                        }), a[s](function () {
                            t.next()
                        }), t.isIE7 && (k("b", o[0], !1, !0), k("a", o[0], !1, !0), k("b", a[0], !1, !0), k("a", a[0], !1, !0)), t.container.append(o.add(a))
                    }
                }), x(m + i, function () {
                    t._preloadTimeout && clearTimeout(t._preloadTimeout), t._preloadTimeout = setTimeout(function () {
                        t.preloadNearbyImages(), t._preloadTimeout = null
                    }, 16)
                }), x(l + i, function () {
                    o.off(i), t.wrap.off("click" + i), t.arrowLeft && r && t.arrowLeft.add(t.arrowRight).destroyMfpFastClick(), t.arrowRight = t.arrowLeft = null
                }), void 0) : !1
            }, next: function () {
                t.direction = !0, t.index = K(t.index + 1), t.updateItemHTML()
            }, prev: function () {
                t.direction = !1, t.index = K(t.index - 1), t.updateItemHTML()
            }, goTo: function (e) {
                t.direction = e >= t.index, t.index = e, t.updateItemHTML()
            }, preloadNearbyImages: function () {
                var e, n = t.st.gallery.preload, i = Math.min(n[0], t.items.length), o = Math.min(n[1], t.items.length);
                for (e = 1; (t.direction ? o : i) >= e; e++)t._preloadItem(t.index + e);
                for (e = 1; (t.direction ? i : o) >= e; e++)t._preloadItem(t.index - e)
            }, _preloadItem: function (n) {
                if (n = K(n), !t.items[n].preloaded) {
                    var i = t.items[n];
                    i.parsed || (i = t.parseEl(n)), T("LazyLoad", i), "image" === i.type && (i.img = e('<img class="mfp-img" />').on("load.mfploader", function () {
                        i.hasSize = !0
                    }).on("error.mfploader", function () {
                        i.hasSize = !0, i.loadError = !0, T("LazyLoadError", i)
                    }).attr("src", i.src)), i.preloaded = !0
                }
            }
        }
    });
    var U = "retina";
    e.magnificPopup.registerModule(U, {
        options: {
            replaceSrc: function (e) {
                return e.src.replace(/\.\w+$/, function (e) {
                    return "@2x" + e
                })
            }, ratio: 1
        }, proto: {
            initRetina: function () {
                if (window.devicePixelRatio > 1) {
                    var e = t.st.retina, n = e.ratio;
                    n = isNaN(n) ? n() : n, n > 1 && (x("ImageHasSize." + U, function (e, t) {
                        t.img.css({"max-width": t.img[0].naturalWidth / n, width: "100%"})
                    }), x("ElementParse." + U, function (t, i) {
                        i.src = e.replaceSrc(i, n)
                    }))
                }
            }
        }
    }), function () {
        var t = 1e3, n = "ontouchstart" in window, i = function () {
            I.off("touchmove" + r + " touchend" + r)
        }, o = "mfpFastClick", r = "." + o;
        e.fn.mfpFastClick = function (o) {
            return e(this).each(function () {
                var a, s = e(this);
                if (n) {
                    var l, c, d, u, p, f;
                    s.on("touchstart" + r, function (e) {
                        u = !1, f = 1, p = e.originalEvent ? e.originalEvent.touches[0] : e.touches[0], c = p.clientX, d = p.clientY, I.on("touchmove" + r, function (e) {
                            p = e.originalEvent ? e.originalEvent.touches : e.touches, f = p.length, p = p[0], (Math.abs(p.clientX - c) > 10 || Math.abs(p.clientY - d) > 10) && (u = !0, i())
                        }).on("touchend" + r, function (e) {
                            i(), u || f > 1 || (a = !0, e.preventDefault(), clearTimeout(l), l = setTimeout(function () {
                                a = !1
                            }, t), o())
                        })
                    })
                }
                s.on("click" + r, function () {
                    a || o()
                })
            })
        }, e.fn.destroyMfpFastClick = function () {
            e(this).off("touchstart" + r + " click" + r), n && I.off("touchmove" + r + " touchend" + r)
        }
    }(), _()
})(window.jQuery || window.Zepto);

//TouchSwipe-Jquery-Plugin
(function (a) {
    if (typeof define === "function" && define.amd && define.amd.jQuery) {
        define(["jquery"], a)
    } else {
        a(jQuery)
    }
}(function (f) {
    var p = "left", o = "right", e = "up", x = "down", c = "in", z = "out", m = "none", s = "auto", l = "swipe", t = "pinch", A = "tap", j = "doubletap", b = "longtap", y = "hold", D = "horizontal", u = "vertical", i = "all", r = 10, g = "start", k = "move", h = "end", q = "cancel", a = "ontouchstart" in window, v = window.navigator.msPointerEnabled && !window.navigator.pointerEnabled, d = window.navigator.pointerEnabled || window.navigator.msPointerEnabled, B = "TouchSwipe";
    var n = {
        fingers: 1,
        threshold: 75,
        cancelThreshold: null,
        pinchThreshold: 20,
        maxTimeThreshold: null,
        fingerReleaseThreshold: 250,
        longTapThreshold: 500,
        doubleTapThreshold: 200,
        swipe: null,
        swipeLeft: null,
        swipeRight: null,
        swipeUp: null,
        swipeDown: null,
        swipeStatus: null,
        pinchIn: null,
        pinchOut: null,
        pinchStatus: null,
        click: null,
        tap: null,
        doubleTap: null,
        longTap: null,
        hold: null,
        triggerOnTouchEnd: true,
        triggerOnTouchLeave: false,
        allowPageScroll: "auto",
        fallbackToMouseEvents: true,
        excludedElements: "label, button, input, select, textarea, a, .noSwipe"
    };
    f.fn.swipe = function (G) {
        var F = f(this), E = F.data(B);
        if (E && typeof G === "string") {
            if (E[G]) {
                return E[G].apply(this, Array.prototype.slice.call(arguments, 1))
            } else {
                f.error("Method " + G + " does not exist on jQuery.swipe")
            }
        } else {
            if (!E && (typeof G === "object" || !G)) {
                return w.apply(this, arguments)
            }
        }
        return F
    };
    f.fn.swipe.defaults = n;
    f.fn.swipe.phases = {PHASE_START: g, PHASE_MOVE: k, PHASE_END: h, PHASE_CANCEL: q};
    f.fn.swipe.directions = {LEFT: p, RIGHT: o, UP: e, DOWN: x, IN: c, OUT: z};
    f.fn.swipe.pageScroll = {NONE: m, HORIZONTAL: D, VERTICAL: u, AUTO: s};
    f.fn.swipe.fingers = {ONE: 1, TWO: 2, THREE: 3, ALL: i};
    function w(E) {
        if (E && (E.allowPageScroll === undefined && (E.swipe !== undefined || E.swipeStatus !== undefined))) {
            E.allowPageScroll = m
        }
        if (E.click !== undefined && E.tap === undefined) {
            E.tap = E.click
        }
        if (!E) {
            E = {}
        }
        E = f.extend({}, f.fn.swipe.defaults, E);
        return this.each(function () {
            var G = f(this);
            var F = G.data(B);
            if (!F) {
                F = new C(this, E);
                G.data(B, F)
            }
        })
    }

    function C(a4, av) {
        var az = (a || d || !av.fallbackToMouseEvents), J = az ? (d ? (v ? "MSPointerDown" : "pointerdown") : "touchstart") : "mousedown", ay = az ? (d ? (v ? "MSPointerMove" : "pointermove") : "touchmove") : "mousemove", U = az ? (d ? (v ? "MSPointerUp" : "pointerup") : "touchend") : "mouseup", S = az ? null : "mouseleave", aD = (d ? (v ? "MSPointerCancel" : "pointercancel") : "touchcancel");
        var ag = 0, aP = null, ab = 0, a1 = 0, aZ = 0, G = 1, aq = 0, aJ = 0, M = null;
        var aR = f(a4);
        var Z = "start";
        var W = 0;
        var aQ = null;
        var T = 0, a2 = 0, a5 = 0, ad = 0, N = 0;
        var aW = null, af = null;
        try {
            aR.bind(J, aN);
            aR.bind(aD, a9)
        } catch (ak) {
            f.error("events not supported " + J + "," + aD + " on jQuery.swipe")
        }
        this.enable = function () {
            aR.bind(J, aN);
            aR.bind(aD, a9);
            return aR
        };
        this.disable = function () {
            aK();
            return aR
        };
        this.destroy = function () {
            aK();
            aR.data(B, null);
            return aR
        };
        this.option = function (bc, bb) {
            if (av[bc] !== undefined) {
                if (bb === undefined) {
                    return av[bc]
                } else {
                    av[bc] = bb
                }
            } else {
                f.error("Option " + bc + " does not exist on jQuery.swipe.options")
            }
            return null
        };
        function aN(bd) {
            if (aB()) {
                return
            }
            if (f(bd.target).closest(av.excludedElements, aR).length > 0) {
                return
            }
            var be = bd.originalEvent ? bd.originalEvent : bd;
            var bc, bb = a ? be.touches[0] : be;
            Z = g;
            if (a) {
                W = be.touches.length
            } else {
                bd.preventDefault()
            }
            ag = 0;
            aP = null;
            aJ = null;
            ab = 0;
            a1 = 0;
            aZ = 0;
            G = 1;
            aq = 0;
            aQ = aj();
            M = aa();
            R();
            if (!a || (W === av.fingers || av.fingers === i) || aX()) {
                ai(0, bb);
                T = at();
                if (W == 2) {
                    ai(1, be.touches[1]);
                    a1 = aZ = au(aQ[0].start, aQ[1].start)
                }
                if (av.swipeStatus || av.pinchStatus) {
                    bc = O(be, Z)
                }
            } else {
                bc = false
            }
            if (bc === false) {
                Z = q;
                O(be, Z);
                return bc
            } else {
                if (av.hold) {
                    af = setTimeout(f.proxy(function () {
                        aR.trigger("hold", [be.target]);
                        if (av.hold) {
                            bc = av.hold.call(aR, be, be.target)
                        }
                    }, this), av.longTapThreshold)
                }
                ao(true)
            }
            return null
        }

        function a3(be) {
            var bh = be.originalEvent ? be.originalEvent : be;
            if (Z === h || Z === q || am()) {
                return
            }
            var bd, bc = a ? bh.touches[0] : bh;
            var bf = aH(bc);
            a2 = at();
            if (a) {
                W = bh.touches.length
            }
            if (av.hold) {
                clearTimeout(af)
            }
            Z = k;
            if (W == 2) {
                if (a1 == 0) {
                    ai(1, bh.touches[1]);
                    a1 = aZ = au(aQ[0].start, aQ[1].start)
                } else {
                    aH(bh.touches[1]);
                    aZ = au(aQ[0].end, aQ[1].end);
                    aJ = ar(aQ[0].end, aQ[1].end)
                }
                G = a7(a1, aZ);
                aq = Math.abs(a1 - aZ)
            }
            if ((W === av.fingers || av.fingers === i) || !a || aX()) {
                aP = aL(bf.start, bf.end);
                al(be, aP);
                ag = aS(bf.start, bf.end);
                ab = aM();
                aI(aP, ag);
                if (av.swipeStatus || av.pinchStatus) {
                    bd = O(bh, Z)
                }
                if (!av.triggerOnTouchEnd || av.triggerOnTouchLeave) {
                    var bb = true;
                    if (av.triggerOnTouchLeave) {
                        var bg = aY(this);
                        bb = E(bf.end, bg)
                    }
                    if (!av.triggerOnTouchEnd && bb) {
                        Z = aC(k)
                    } else {
                        if (av.triggerOnTouchLeave && !bb) {
                            Z = aC(h)
                        }
                    }
                    if (Z == q || Z == h) {
                        O(bh, Z)
                    }
                }
            } else {
                Z = q;
                O(bh, Z)
            }
            if (bd === false) {
                Z = q;
                O(bh, Z)
            }
        }

        function L(bb) {
            var bc = bb.originalEvent;
            if (a) {
                if (bc.touches.length > 0) {
                    F();
                    return true
                }
            }
            if (am()) {
                W = ad
            }
            a2 = at();
            ab = aM();
            if (ba() || !an()) {
                Z = q;
                O(bc, Z)
            } else {
                if (av.triggerOnTouchEnd || (av.triggerOnTouchEnd == false && Z === k)) {
                    bb.preventDefault();
                    Z = h;
                    O(bc, Z)
                } else {
                    if (!av.triggerOnTouchEnd && a6()) {
                        Z = h;
                        aF(bc, Z, A)
                    } else {
                        if (Z === k) {
                            Z = q;
                            O(bc, Z)
                        }
                    }
                }
            }
            ao(false);
            return null
        }

        function a9() {
            W = 0;
            a2 = 0;
            T = 0;
            a1 = 0;
            aZ = 0;
            G = 1;
            R();
            ao(false)
        }

        function K(bb) {
            var bc = bb.originalEvent;
            if (av.triggerOnTouchLeave) {
                Z = aC(h);
                O(bc, Z)
            }
        }

        function aK() {
            aR.unbind(J, aN);
            aR.unbind(aD, a9);
            aR.unbind(ay, a3);
            aR.unbind(U, L);
            if (S) {
                aR.unbind(S, K)
            }
            ao(false)
        }

        function aC(bf) {
            var be = bf;
            var bd = aA();
            var bc = an();
            var bb = ba();
            if (!bd || bb) {
                be = q
            } else {
                if (bc && bf == k && (!av.triggerOnTouchEnd || av.triggerOnTouchLeave)) {
                    be = h
                } else {
                    if (!bc && bf == h && av.triggerOnTouchLeave) {
                        be = q
                    }
                }
            }
            return be
        }

        function O(bd, bb) {
            var bc = undefined;
            if (I() || V()) {
                bc = aF(bd, bb, l)
            } else {
                if ((P() || aX()) && bc !== false) {
                    bc = aF(bd, bb, t)
                }
            }
            if (aG() && bc !== false) {
                bc = aF(bd, bb, j)
            } else {
                if (ap() && bc !== false) {
                    bc = aF(bd, bb, b)
                } else {
                    if (ah() && bc !== false) {
                        bc = aF(bd, bb, A)
                    }
                }
            }
            if (bb === q) {
                a9(bd)
            }
            if (bb === h) {
                if (a) {
                    if (bd.touches.length == 0) {
                        a9(bd)
                    }
                } else {
                    a9(bd)
                }
            }
            return bc
        }

        function aF(be, bb, bd) {
            var bc = undefined;
            if (bd == l) {
                aR.trigger("swipeStatus", [bb, aP || null, ag || 0, ab || 0, W, aQ]);
                if (av.swipeStatus) {
                    bc = av.swipeStatus.call(aR, be, bb, aP || null, ag || 0, ab || 0, W, aQ);
                    if (bc === false) {
                        return false
                    }
                }
                if (bb == h && aV()) {
                    aR.trigger("swipe", [aP, ag, ab, W, aQ]);
                    if (av.swipe) {
                        bc = av.swipe.call(aR, be, aP, ag, ab, W, aQ);
                        if (bc === false) {
                            return false
                        }
                    }
                    switch (aP) {
                        case p:
                            aR.trigger("swipeLeft", [aP, ag, ab, W, aQ]);
                            if (av.swipeLeft) {
                                bc = av.swipeLeft.call(aR, be, aP, ag, ab, W, aQ)
                            }
                            break;
                        case o:
                            aR.trigger("swipeRight", [aP, ag, ab, W, aQ]);
                            if (av.swipeRight) {
                                bc = av.swipeRight.call(aR, be, aP, ag, ab, W, aQ)
                            }
                            break;
                        case e:
                            aR.trigger("swipeUp", [aP, ag, ab, W, aQ]);
                            if (av.swipeUp) {
                                bc = av.swipeUp.call(aR, be, aP, ag, ab, W, aQ)
                            }
                            break;
                        case x:
                            aR.trigger("swipeDown", [aP, ag, ab, W, aQ]);
                            if (av.swipeDown) {
                                bc = av.swipeDown.call(aR, be, aP, ag, ab, W, aQ)
                            }
                            break
                    }
                }
            }
            if (bd == t) {
                aR.trigger("pinchStatus", [bb, aJ || null, aq || 0, ab || 0, W, G, aQ]);
                if (av.pinchStatus) {
                    bc = av.pinchStatus.call(aR, be, bb, aJ || null, aq || 0, ab || 0, W, G, aQ);
                    if (bc === false) {
                        return false
                    }
                }
                if (bb == h && a8()) {
                    switch (aJ) {
                        case c:
                            aR.trigger("pinchIn", [aJ || null, aq || 0, ab || 0, W, G, aQ]);
                            if (av.pinchIn) {
                                bc = av.pinchIn.call(aR, be, aJ || null, aq || 0, ab || 0, W, G, aQ)
                            }
                            break;
                        case z:
                            aR.trigger("pinchOut", [aJ || null, aq || 0, ab || 0, W, G, aQ]);
                            if (av.pinchOut) {
                                bc = av.pinchOut.call(aR, be, aJ || null, aq || 0, ab || 0, W, G, aQ)
                            }
                            break
                    }
                }
            }
            if (bd == A) {
                if (bb === q || bb === h) {
                    clearTimeout(aW);
                    clearTimeout(af);
                    if (Y() && !H()) {
                        N = at();
                        aW = setTimeout(f.proxy(function () {
                            N = null;
                            aR.trigger("tap", [be.target]);
                            if (av.tap) {
                                bc = av.tap.call(aR, be, be.target)
                            }
                        }, this), av.doubleTapThreshold)
                    } else {
                        N = null;
                        aR.trigger("tap", [be.target]);
                        if (av.tap) {
                            bc = av.tap.call(aR, be, be.target)
                        }
                    }
                }
            } else {
                if (bd == j) {
                    if (bb === q || bb === h) {
                        clearTimeout(aW);
                        N = null;
                        aR.trigger("doubletap", [be.target]);
                        if (av.doubleTap) {
                            bc = av.doubleTap.call(aR, be, be.target)
                        }
                    }
                } else {
                    if (bd == b) {
                        if (bb === q || bb === h) {
                            clearTimeout(aW);
                            N = null;
                            aR.trigger("longtap", [be.target]);
                            if (av.longTap) {
                                bc = av.longTap.call(aR, be, be.target)
                            }
                        }
                    }
                }
            }
            return bc
        }

        function an() {
            var bb = true;
            if (av.threshold !== null) {
                bb = ag >= av.threshold
            }
            return bb
        }

        function ba() {
            var bb = false;
            if (av.cancelThreshold !== null && aP !== null) {
                bb = (aT(aP) - ag) >= av.cancelThreshold
            }
            return bb
        }

        function ae() {
            if (av.pinchThreshold !== null) {
                return aq >= av.pinchThreshold
            }
            return true
        }

        function aA() {
            var bb;
            if (av.maxTimeThreshold) {
                if (ab >= av.maxTimeThreshold) {
                    bb = false
                } else {
                    bb = true
                }
            } else {
                bb = true
            }
            return bb
        }

        function al(bb, bc) {
            if (av.allowPageScroll === m || aX()) {
                bb.preventDefault()
            } else {
                var bd = av.allowPageScroll === s;
                switch (bc) {
                    case p:
                        if ((av.swipeLeft && bd) || (!bd && av.allowPageScroll != D)) {
                            bb.preventDefault()
                        }
                        break;
                    case o:
                        if ((av.swipeRight && bd) || (!bd && av.allowPageScroll != D)) {
                            bb.preventDefault()
                        }
                        break;
                    case e:
                        if ((av.swipeUp && bd) || (!bd && av.allowPageScroll != u)) {
                            bb.preventDefault()
                        }
                        break;
                    case x:
                        if ((av.swipeDown && bd) || (!bd && av.allowPageScroll != u)) {
                            bb.preventDefault()
                        }
                        break
                }
            }
        }

        function a8() {
            var bc = aO();
            var bb = X();
            var bd = ae();
            return bc && bb && bd
        }

        function aX() {
            return !!(av.pinchStatus || av.pinchIn || av.pinchOut)
        }

        function P() {
            return !!(a8() && aX())
        }

        function aV() {
            var be = aA();
            var bg = an();
            var bd = aO();
            var bb = X();
            var bc = ba();
            var bf = !bc && bb && bd && bg && be;
            return bf
        }

        function V() {
            return !!(av.swipe || av.swipeStatus || av.swipeLeft || av.swipeRight || av.swipeUp || av.swipeDown)
        }

        function I() {
            return !!(aV() && V())
        }

        function aO() {
            return ((W === av.fingers || av.fingers === i) || !a)
        }

        function X() {
            return aQ[0].end.x !== 0
        }

        function a6() {
            return !!(av.tap)
        }

        function Y() {
            return !!(av.doubleTap)
        }

        function aU() {
            return !!(av.longTap)
        }

        function Q() {
            if (N == null) {
                return false
            }
            var bb = at();
            return (Y() && ((bb - N) <= av.doubleTapThreshold))
        }

        function H() {
            return Q()
        }

        function ax() {
            return ((W === 1 || !a) && (isNaN(ag) || ag < av.threshold))
        }

        function a0() {
            return ((ab > av.longTapThreshold) && (ag < r))
        }

        function ah() {
            return !!(ax() && a6())
        }

        function aG() {
            return !!(Q() && Y())
        }

        function ap() {
            return !!(a0() && aU())
        }

        function F() {
            a5 = at();
            ad = event.touches.length + 1
        }

        function R() {
            a5 = 0;
            ad = 0
        }

        function am() {
            var bb = false;
            if (a5) {
                var bc = at() - a5;
                if (bc <= av.fingerReleaseThreshold) {
                    bb = true
                }
            }
            return bb
        }

        function aB() {
            return !!(aR.data(B + "_intouch") === true)
        }

        function ao(bb) {
            if (bb === true) {
                aR.bind(ay, a3);
                aR.bind(U, L);
                if (S) {
                    aR.bind(S, K)
                }
            } else {
                aR.unbind(ay, a3, false);
                aR.unbind(U, L, false);
                if (S) {
                    aR.unbind(S, K, false)
                }
            }
            aR.data(B + "_intouch", bb === true)
        }

        function ai(bc, bb) {
            var bd = bb.identifier !== undefined ? bb.identifier : 0;
            aQ[bc].identifier = bd;
            aQ[bc].start.x = aQ[bc].end.x = bb.pageX || bb.clientX;
            aQ[bc].start.y = aQ[bc].end.y = bb.pageY || bb.clientY;
            return aQ[bc]
        }

        function aH(bb) {
            var bd = bb.identifier !== undefined ? bb.identifier : 0;
            var bc = ac(bd);
            bc.end.x = bb.pageX || bb.clientX;
            bc.end.y = bb.pageY || bb.clientY;
            return bc
        }

        function ac(bc) {
            for (var bb = 0; bb < aQ.length; bb++) {
                if (aQ[bb].identifier == bc) {
                    return aQ[bb]
                }
            }
        }

        function aj() {
            var bb = [];
            for (var bc = 0; bc <= 5; bc++) {
                bb.push({start: {x: 0, y: 0}, end: {x: 0, y: 0}, identifier: 0})
            }
            return bb
        }

        function aI(bb, bc) {
            bc = Math.max(bc, aT(bb));
            M[bb].distance = bc
        }

        function aT(bb) {
            if (M[bb]) {
                return M[bb].distance
            }
            return undefined
        }

        function aa() {
            var bb = {};
            bb[p] = aw(p);
            bb[o] = aw(o);
            bb[e] = aw(e);
            bb[x] = aw(x);
            return bb
        }

        function aw(bb) {
            return {direction: bb, distance: 0}
        }

        function aM() {
            return a2 - T
        }

        function au(be, bd) {
            var bc = Math.abs(be.x - bd.x);
            var bb = Math.abs(be.y - bd.y);
            return Math.round(Math.sqrt(bc * bc + bb * bb))
        }

        function a7(bb, bc) {
            var bd = (bc / bb) * 1;
            return bd.toFixed(2)
        }

        function ar() {
            if (G < 1) {
                return z
            } else {
                return c
            }
        }

        function aS(bc, bb) {
            return Math.round(Math.sqrt(Math.pow(bb.x - bc.x, 2) + Math.pow(bb.y - bc.y, 2)))
        }

        function aE(be, bc) {
            var bb = be.x - bc.x;
            var bg = bc.y - be.y;
            var bd = Math.atan2(bg, bb);
            var bf = Math.round(bd * 180 / Math.PI);
            if (bf < 0) {
                bf = 360 - Math.abs(bf)
            }
            return bf
        }

        function aL(bc, bb) {
            var bd = aE(bc, bb);
            if ((bd <= 45) && (bd >= 0)) {
                return p
            } else {
                if ((bd <= 360) && (bd >= 315)) {
                    return p
                } else {
                    if ((bd >= 135) && (bd <= 225)) {
                        return o
                    } else {
                        if ((bd > 45) && (bd < 135)) {
                            return x
                        } else {
                            return e
                        }
                    }
                }
            }
        }

        function at() {
            var bb = new Date();
            return bb.getTime()
        }

        function aY(bb) {
            bb = f(bb);
            var bd = bb.offset();
            var bc = {left: bd.left, right: bd.left + bb.outerWidth(), top: bd.top, bottom: bd.top + bb.outerHeight()};
            return bc
        }

        function E(bb, bc) {
            return (bb.x > bc.left && bb.x < bc.right && bb.y > bc.top && bb.y < bc.bottom)
        }
    }
}));

jQuery(function ($) {

    $(':header').each(function () {
        $(this).addClass('heading-style-' + $(this).prop('tagName').replace(/^\D+/g, ''));
    });

    // Turn radios into btn-group
    $('.radio.btn-group label').addClass('btn');
    $(".btn-group label:not(.active)").click(function () {
        var label = $(this);
        var input = $('#' + label.attr('for'));

        if (!input.prop('checked')) {
            label.closest('.btn-group').find("label").removeClass('active btn-success btn-danger btn-primary');
            if (input.val() == '') {
                label.addClass('active btn-primary');
            } else if (input.val() == 0) {
                label.addClass('active btn-danger');
            } else {
                label.addClass('active btn-success');
            }
            input.prop('checked', true);
        }
    });
    $(".btn-group input[checked]").each(function () {
        if ($(this).val() == '') {
            $("label[for=" + $(this).attr('id') + "]").addClass('active btn-primary');
        } else if ($(this).val() == 0) {
            $("label[for=" + $(this).attr('id') + "]").addClass('active btn-danger');
        } else {
            $("label[for=" + $(this).attr('id') + "]").addClass('active btn-success');
        }
    });

    $('.accordion-body.collapse').on('shown', function (event) {
        $(this).parent('.accordion-group').find('.accordion-toggle').addClass('selected')
    });
    $('.accordion-body.collapse').on('hidden', function (event) {
        $(this).parent('.accordion-group').find('.accordion-toggle').removeClass('selected')
    });

    $('ul li:last-child').addClass('lastItem');
    $('ul li:first-child').addClass('firstItem');

    /*Pagination Active Button*/
    $('div.pagination ul li:not([class])').addClass('num');

    // Modal Window
    $('[href="#modal"]').click(function (e) {
        $('#modal').modal({
            show: true,
            backdrop: false
        });
        e.preventDefault();
    });

    $('#modal button.modalClose').click(function (e) {
        $('#modal').modal('hide');
        e.preventDefault();
    });
    $('.modal-hide').click(function (e) {
        $('#modal').modal('hide');
    });

    var iconTest = /icon-/i;
    var iconReplace = "fa fa-";

    function iconSet() {
        $('[class*="icon-"]').each(function () {
            iconClass = $(this).attr('class');
            var newString = iconClass.replace(iconTest, iconReplace);
            $(this).addClass(newString);
        })
    }

    iconSet()


    //$(window).load(function(){
    function setOffset() {
        $('.item__module, .item, .moduletable, .page_header').each(function (i) {
            var scrollDown = ($(this).offset().top - $(window).scrollTop()) < $(window).height(),
                scrollUp = ($(this).offset().top - $(window).scrollTop()) > -$(this).height();
            if (scrollDown && scrollUp) {
                $(this).addClass('visible');
                $(this).addClass('visible-first');
            } else {
                $(this).removeClass('visible');
            }
        });

    }

    setOffset()
    $(window).resize($.throttle(500, setOffset));
    $(window).scroll($.throttle(500, setOffset));
    //});


    // hide #back-top first
    $("#back-top").hide();

    // fade in #back-top
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('#back-top').fadeIn();
        } else {
            $('#back-top').fadeOut();
        }
        if ($(this).scrollTop() > 24) {
            $('body:first').addClass('scrolled');
        }
        else {
            $('body:first').removeClass('scrolled');
        }
    });

    var $scrollEl = ($.browser.mozilla || $.browser.msie) ? $('html') : $('body');


    // scroll body to 0px on click
    $('#back-top a').click(function () {
        $scrollEl.animate({
            scrollTop: 0
        }, 400);
        return false;
    });

    // Calendar position fix
    if ($('#jform_profile_dob_img').length && $.browser.msie) {
        var h = $('#jform_profile_dob_img').offset().top - 202;
        $('head').append('<style> .calendar { top:' + h + 'px !important;}</style>');
    }

    if ($.browser.msie) {
        $('.lazy_container').each(function () {
            $(this).parent('a').not('.fancybox-thumb').attr({style: $(this).attr('style')}).parent('.img-intro__left, .img-intro__right').attr({style: $(this).attr('style')});
        })
    }
    $('body').not('.com_config').find('select').each(function () {
        if (!$(this).parent().hasClass('select') && !$(this).attr('multiple')) {
            $(this).wrap('<span class="select"/>')
        }
    })
    $('select.kchecktask').change(function () {
        ktarget = $(this).parents('form').find('select[name=target]');
        if ($(this).val() == 'move') {
            ktarget.removeAttr('disabled');
        } else {
            ktarget.attr('disabled', 'disabled');
        }
    });
    $('body').not('.com_config').find('input[type=checkbox]').each(function (i) {
        if ($(this).parent().not('span.checkbox')) {
            if (!$(this).attr("id")) {
                $(this).attr({id: 'checkbox' + i}).wrap('<span class="checkbox"/>').after('<label class="checkbox_inner" for="checkbox' + i + '" />');
            }
            else {
                $(this).wrap('<span class="checkbox"/>').after('<label class="checkbox_inner" for="' + $(this).attr("id") + '" />');
            }
        }
    })
    $('body').not('.com_config').find('input[type=radio]').each(function (i) {
        if ($(this).parent().not('span.radio')) {
            if (!$(this).attr("id")) {
                $(this).attr({id: 'radio' + i}).wrap('<span class="radio"/>').after('<label class="radio_inner" for="radio' + i + '" />');
            }
            else {
                $(this).wrap('<span class="radio"/>').after('<label class="radio_inner" for="' + $(this).attr("id") + '" />');
            }
        }
    })
    $('.page-category__faqs .items-row').not('.row-0').find('.span12').prepend('<div class="to_top"><a href="#"><i class="fa fa-chevron-up"></i></a></div>').find('.to_top a').click(function () {
        $scrollEl.animate({
            scrollTop: 0
        }, 400);
        return false;
    })

    $(window).load(function () {
        function scrollFixed() {
            if ($(window).scrollTop() == 0) {
                $('#top').removeClass('fixed');
            } else {
                $('#top').addClass('fixed');
            }
        }

        $(window).scroll(scrollFixed);
        $(window).resize(scrollFixed);
        scrollFixed();
    })

    $('ul li a, ol li a').not("[data-toggle='tab']").not('.pagenav').hover(function () {
        $(this).parent('li').addClass('active');
    }, function () {
        $(this).parent('li').removeClass('active');
    })

    $('.accordion').each(function () {
        $(this).find('> .accordion-group:last-child').addClass('lastItem')
        $(this).find('> .accordion-group:first-child').addClass('firstItem')
    })

     setInterval(function () {
        $('.top-img .layer-1').addClass('visible');
    }, 1000)
    setInterval(function () {
        $('.top-img .layer-2').addClass('visible');
    }, 2000)
    setInterval(function () {
        $('.top-img .layer-3').addClass('visible');
    }, 3000)

    function parallax() {
        if ($(window).width() > 768) {
            $('.top-img img').css({transform: 'rotateX(' + $(window).scrollTop() / 10 + 'deg)'});
            $('.top-img .no-rotate img').css({transform: 'rotateX(0deg)'});
            $('.top-img .layer-1 img').css({marginTop: -$(window).scrollTop() / 12});
            $('.top-img .layer-2 img').css({marginTop: -$(window).scrollTop() / 8});
            $('.top-img .layer-3 img').css({marginTop: -$(window).scrollTop() / 4});
        } else {
            $('.top-img img').css({transform: 'rotateX(0deg)', marginTop: 0});
        }
    }

    parallax();
    $(window).scroll(parallax);
    $(window).resize(parallax);

    $(window).load(function () {
        $('iframe').each(function () {
            var url = $(this).attr("src");
            if (url) {
                if (url.indexOf('www.youtube.com')) {
                    var separator = (url.indexOf('?') > 0) ? '&' : '?';
                    $(this).attr('src', url + separator + 'wmode=transparent');
                }
            }
        });


        if ($.browser.msie && $.browser.version < 10) {

            $('#fixed-sidebar-right .mod-login_userdata input').each(function () {
                this.value = this.getAttribute('placeholder');
                $(this).blur(function () {
                    if (!this.value) this.value = this.getAttribute('placeholder');
                }).focus(function () {
                    if (this.value == this.getAttribute('placeholder')) this.value = "";
                });
            });


        }

    });

});