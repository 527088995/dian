/** EasyWeb iframe v3.1.1 data:2019-03-24 License By http://easyweb.vip */

layui.define(["layer"], function(f) {
	var h = layui.jquery;
	var k = layui.layer;
	var a = ".layui-layout-admin>.layui-body";
	var l = a + ">.layui-tab";
	var e = ".layui-layout-admin>.layui-side>.layui-side-scroll";
	var j = ".layui-layout-admin>.layui-header";
	var b = "admin-pagetabs";
	var d = "admin-side-nav";
	var c = "theme-admin";
	var m = {
		defaultTheme: "theme-admin",
		tableName: "easyweb",
		flexible: function(n) {
			var o = h(".layui-layout-admin").hasClass("admin-nav-mini");
			if (o == !n) {
				return
			}
			if (n) {
				h(".layui-layout-admin").removeClass("admin-nav-mini")
			} else {
				h(".layui-layout-admin").addClass("admin-nav-mini")
			}
			m.removeNavHover()
		},
		activeNav: function(n) {
			if (!n) {
				n = window.location.pathname;
				n = n.substring(n.indexOf("/"))
			}
			if (n && n != "") {
				h(e + ">.layui-nav .layui-nav-item .layui-nav-child dd").removeClass("layui-this");
				h(e + ">.layui-nav .layui-nav-item").removeClass("layui-this");
				var r = h(e + '>.layui-nav a[lay-href="' + n + '"]');
				if (r && r.length > 0) {
					if (h(e + ">.layui-nav").attr("lay-accordion") == "true") {
						h(e + ">.layui-nav .layui-nav-itemed").removeClass("layui-nav-itemed")
					}
					r.parent().addClass("layui-this");
					r.parent("dd").parents(".layui-nav-child").parent().addClass("layui-nav-itemed");
					h('ul[lay-filter="' + d + '"]').addClass("layui-hide");
					var p = r.parents(".layui-nav");
					p.removeClass("layui-hide");
					h(j + ">.layui-nav>.layui-nav-item").removeClass("layui-this");
					h(j + '>.layui-nav>.layui-nav-item>a[nav-bind="' + p.attr("nav-id") + '"]').parent().addClass("layui-this");
					var o = r.offset().top + r.outerHeight() + 30 - m.getPageHeight();
					var q = 50 + 65 - r.offset().top;
					if (o > 0) {
						h(e).animate({
							"scrollTop": h(e).scrollTop() + o
						}, 100)
					} else {
						if (q > 0) {
							h(e).animate({
								"scrollTop": h(e).scrollTop() - q
							}, 100)
						}
					}
				} else {}
			} else {
				console.warn("active url is null")
			}
		},
		popupRight: function(n) {
			if (n.title == undefined) {
				n.title = false;
				n.closeBtn = false
			}
			if (n.anim == undefined) {
				n.anim = 2
			}
			if (n.fixed == undefined) {
				n.fixed = true
			}
			n.isOutAnim = false;
			n.offset = "r";
			n.shadeClose = true;
			n.area = "336px";
			n.skin = "layui-layer-adminRight";
			n.move = false;
			return m.open(n)
		},
		open: function(o) {
			if (!o.area) {
				o.area = (o.type == 2) ? ["360px", "300px"] : "360px"
			}
			if (!o.skin) {
				o.skin = "layui-layer-admin"
			}
			if (!o.offset) {
				o.offset = "35px"
			}
			if (o.fixed == undefined) {
				o.fixed = false
			}
			o.resize = o.resize != undefined ? o.resize : false;
			o.shade = o.shade != undefined ? o.shade : 0.1;
			var n = o.end;
			o.end = function() {
				k.closeAll("tips");
				n && n()
			};
			return k.open(o)
		},
		req: function(n, o, p, q) {
			m.ajax({
				url: n,
				data: o,
				type: q,
				dataType: "json",
				success: p
			})
		},
		ajax: function(o) {
			var n = o.success;
			o.success = function(p, q, s) {
				var r;
				if ("json" == o.dataType.toLowerCase()) {
					r = p
				} else {
					r = m.parseJSON(p)
				}
				r && (r = p);
				if (m.ajaxSuccessBefore(r, o.url) == false) {
					return
				}
				n(p, q, s)
			};
			o.error = function(p) {
				o.success({
					code: p.status,
					msg: p.statusText
				})
			};
			o.beforeSend = function(r) {
				var q = m.getAjaxHeaders(o.url);
				for (var p = 0; p < q.length; p++) {
					r.setRequestHeader(q[p].name, q[p].value)
				}
			};
			h.ajax(o)
		},
		ajaxSuccessBefore: function(n, o) {
			return true
		},
		getAjaxHeaders: function(n) {
			var o = new Array();
			return o
		},
		parseJSON: function(p) {
			if (typeof p == "string") {
				try {
					var o = JSON.parse(p);
					if (typeof o == "object" && o) {
						return o
					}
				} catch (n) {}
			}
		},
		showLoading: function(q, p, o) {
			var n = ['<div class="ball-loader"><span></span><span></span><span></span><span></span></div>', '<div class="rubik-loader"></div>'];
			if (!q) {
				q = "body"
			}
			if (p == undefined) {
				p = 1
			}
			h(q).addClass("page-no-scroll");
			var r = h(q).children(".page-loading");
			if (r.length <= 0) {
				h(q).append('<div class="page-loading">' + n[p - 1] + "</div>");
				r = h(q).children(".page-loading")
			}
			o && r.css("background-color", "rgba(255,255,255," + o + ")");
			r.show()
		},
		removeLoading: function(o, q, n) {
			if (!o) {
				o = "body"
			}
			if (q == undefined) {
				q = true
			}
			var p = h(o).children(".page-loading");
			if (n) {
				p.remove()
			} else {
				q ? p.fadeOut() : p.hide()
			}
			h(o).removeClass("page-no-scroll")
		},
		putTempData: function(n, o) {
			if (o != undefined && o != null) {
				layui.sessionData("tempData", {
					key: n,
					value: o
				})
			} else {
				layui.sessionData("tempData", {
					key: n,
					remove: true
				})
			}
		},
		getTempData: function(n) {
			var o = layui.sessionData("tempData");
			if (o) {
				return o[n]
			} else {
				return false
			}
		},
		rollPage: function(q) {
			var o = h(l + ">.layui-tab-title");
			var p = o.scrollLeft();
			if ("left" === q) {
				o.animate({
					"scrollLeft": p - 120
				}, 100)
			} else {
				if ("auto" === q) {
					var n = 0;
					o.children("li").each(function() {
						if (h(this).hasClass("layui-this")) {
							return false
						} else {
							n += h(this).outerWidth()
						}
					});
					o.animate({
						"scrollLeft": n - 120
					}, 100)
				} else {
					o.animate({
						"scrollLeft": p + 120
					}, 100)
				}
			}
		},
		refresh: function(n) {
			var p;
			if (!n) {
				p = h(l + ">.layui-tab-content>.layui-tab-item.layui-show>.admin-iframe");
				if (!p || p.length <= 0) {
					p = h(a + ">div>.admin-iframe")
				}
			} else {
				p = h(l + '>.layui-tab-content>.layui-tab-item>.admin-iframe[lay-id="' + n + '"]');
				if (!p || p.length <= 0) {
					p = h(a + ">.admin-iframe")
				}
			}
			if (p && p[0]) {
				try {
					p[0].contentWindow.location.reload(true)
				} catch (o) {
					p.attr("src", p.attr("src"))
				}
			} else {
				console.warn(n + " is not found")
			}
		},
		closeThisTabs: function(n) {
			m.closeTabOperNav();
			var o = h(l + ">.layui-tab-title");
			if (!n) {
				if (o.find("li").first().hasClass("layui-this")) {
					k.msg("主页不能关闭", {
						icon: 2
					});
					return
				}
				o.find("li.layui-this").find(".layui-tab-close").trigger("click")
			} else {
				if (n == o.find("li").first().attr("lay-id")) {
					k.msg("主页不能关闭", {
						icon: 2
					});
					return
				}
				o.find('li[lay-id="' + n + '"]').find(".layui-tab-close").trigger("click")
			}
		},
		closeOtherTabs: function(n) {
			if (!n) {
				h(l + ">.layui-tab-title li:gt(0):not(.layui-this)").find(".layui-tab-close").trigger("click")
			} else {
				h(l + ">.layui-tab-title li:gt(0)").each(function() {
					if (n != h(this).attr("lay-id")) {
						h(this).find(".layui-tab-close").trigger("click")
					}
				})
			}
			m.closeTabOperNav()
		},
		closeAllTabs: function() {
			h(l + ">.layui-tab-title li:gt(0)").find(".layui-tab-close").trigger("click");
			h(l + ">.layui-tab-title li:eq(0)").trigger("click");
			m.closeTabOperNav()
		},
		closeTabOperNav: function() {
			h(".layui-icon-down .layui-nav .layui-nav-child").removeClass("layui-show")
		},
		changeTheme: function(t) {
			if (t) {
				layui.data(m.tableName, {
					key: "theme",
					value: t
				});
				if (c == t) {
					t = undefined
				}
			} else {
				layui.data(m.tableName, {
					key: "theme",
					remove: true
				})
			}
			m.removeTheme(top);
			!t || top.layui.link(m.getThemeDir() + t + ".css", t);
			var u = top.window.frames;
			for (var p = 0; p < u.length; p++) {
				var r = u[p];
				try {
					m.removeTheme(r)
				} catch (s) {}
				if (t && r.layui) {
					r.layui.link(m.getThemeDir() + t + ".css", t)
				}
				var q = r.frames;
				for (var o = 0; o < q.length; o++) {
					var n = q[o];
					try {
						m.removeTheme(n)
					} catch (s) {}
					if (t && n.layui) {
						n.layui.link(m.getThemeDir() + t + ".css", t)
					}
				}
			}
		},
		removeTheme: function(n) {
			if (!n) {
				n = window
			}
			if (n.layui) {
				var o = "layuicss-theme";
				n.layui.jquery('link[id^="' + o + '"]').remove()
			}
		},
		getThemeDir: function() {
			return layui.cache.base + "theme/"
		},
		closeThisDialog: function() {
			parent.layer.close(parent.layer.getFrameIndex(window.name))
		},
		closeDialog: function(n) {
			var o = h(n).parents(".layui-layer").attr("id").substring(11);
			k.close(o)
		},
		iframeAuto: function() {
			parent.layer.iframeAuto(parent.layer.getFrameIndex(window.name))
		},
		getPageHeight: function() {
			return document.documentElement.clientHeight || document.body.clientHeight
		},
		getPageWidth: function() {
			return document.documentElement.clientWidth || document.body.clientWidth
		},
		removeNavHover: function() {
			h(".admin-nav-hover>.layui-nav-child").css({
				"top": "auto",
				"max-height": "none",
				"overflow": "auto"
			});
			h(".admin-nav-hover").removeClass("admin-nav-hover")
		},
		setNavHoverCss: function(p) {
			var n = h(".admin-nav-hover>.layui-nav-child");
			if (p && n.length > 0) {
				var r = (p.offset().top + n.outerHeight()) > window.innerHeight;
				if (r) {
					var o = p.offset().top - n.outerHeight() + p.outerHeight();
					if (o < 50) {
						var q = m.getPageHeight();
						if (p.offset().top < q / 2) {
							n.css({
								"top": "50px",
								"max-height": q - 50 + "px",
								"overflow": "auto"
							})
						} else {
							n.css({
								"top": p.offset().top,
								"max-height": q - p.offset().top,
								"overflow": "auto"
							})
						}
					} else {
						n.css("top", o)
					}
				} else {
					n.css("top", p.offset().top)
				}
				i = true
			}
		}
	};
	m.events = {
		flexible: function(o) {
			var n = h(".layui-layout-admin").hasClass("admin-nav-mini");
			m.flexible(n)
		},
		refresh: function() {
			m.refresh()
		},
		back: function() {
			history.back()
		},
		theme: function() {
			var n = h(this).attr("data-url");
			m.popupRight({
				id: "layer-theme",
				type: 2,
                content: n ? n : Feng.ctxPath + '/system/theme'
			})
		},
		note: function() {
			var n = h(this).attr("data-url");
			m.popupRight({
				id: "layer-note",
				title: "便签",
				type: 2,
				closeBtn: false,
				content: n ? n : "page/tpl/tpl-note.html"
			})
		},
		message: function() {
			var n = h(this).attr("data-url");
			m.popupRight({
				id: "layer-notice",
				type: 2,
				content: n ? n : Feng.ctxPath + '/system/message'
			})
		},
		psw: function() {
			var n = h(this).attr("data-url");
			m.open({
				id: "pswForm",
				type: 2,
				title: "修改密码",
				shade: 0,
				content: n ? n : "page/tpl/tpl-password.html"
			})
		},
		logout: function() {
			var n = h(this).attr("data-url");
			k.confirm("确定要退出登录吗？", {
				title: "温馨提示",
				skin: "layui-layer-admin"
			}, function() {
				location.replace(n ? n : "/")
			})
		},
		fullScreen: function(t) {
			var v = "layui-icon-screen-full",
				p = "layui-icon-screen-restore";
			var n = h(this).find("i");
			var s = document.fullscreenElement || document.msFullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || false;
			if (s) {
				var r = document.exitFullscreen || document.webkitExitFullscreen || document.mozCancelFullScreen || document.msExitFullscreen;
				if (r) {
					r.call(document)
				} else {
					if (window.ActiveXObject) {
						var u = new ActiveXObject("WScript.Shell");
						u && u.SendKeys("{F11}")
					}
				}
				n.addClass(v).removeClass(p)
			} else {
				var o = document.documentElement;
				var q = o.requestFullscreen || o.webkitRequestFullscreen || o.mozRequestFullScreen || o.msRequestFullscreen;
				if (q) {
					q.call(o)
				} else {
					if (window.ActiveXObject) {
						var u = new ActiveXObject("WScript.Shell");
						u && u.SendKeys("{F11}")
					}
				}
				n.addClass(p).removeClass(v)
			}
		},
		leftPage: function() {
			m.rollPage("left")
		},
		rightPage: function() {
			m.rollPage()
		},
		closeThisTabs: function() {
			m.closeThisTabs()
		},
		closeOtherTabs: function() {
			m.closeOtherTabs()
		},
		closeAllTabs: function() {
			m.closeAllTabs()
		},
		closeDialog: function() {
			m.closeThisDialog()
		},
		closePageDialog: function() {
			m.closeDialog(this)
		}
	};
	h("body").on("click", "*[ew-event]", function() {
		var n = h(this).attr("ew-event");
		var o = m.events[n];
		o && o.call(this, h(this))
	});
	h("body").on("click", "*[ew-href]", function() {
		var n = h(this).attr("ew-href");
		var o = h(this).text();
		top.layui.index.openTab({
			title: o,
			url: n
		})
	});
	h("body").on("mouseenter", "*[lay-tips]", function() {
		var n = h(this).attr("lay-tips");
		var o = h(this).attr("lay-direction");
		var p = h(this).attr("lay-bg");
		k.tips(n, this, {
			tips: [o || 3, p || "#333333"],
			time: -1
		})
	}).on("mouseleave", "*[lay-tips]", function() {
		k.closeAll("tips")
	});
	var i = false;
	h("body").on("mouseenter", ".layui-layout-admin.admin-nav-mini .layui-side .layui-nav .layui-nav-item>a", function() {
		if (m.getPageWidth() > 750) {
			var p = h(this);
			h(".admin-nav-hover>.layui-nav-child").css("top", "auto");
			h(".admin-nav-hover").removeClass("admin-nav-hover");
			p.parent().addClass("admin-nav-hover");
			var n = h(".admin-nav-hover>.layui-nav-child");
			if (n.length > 0) {
				m.setNavHoverCss(p)
			} else {
				var o = p.find("cite").text();
				k.tips(o, p, {
					tips: [2, "#333333"],
					time: -1
				})
			}
		}
	}).on("mouseleave", ".layui-layout-admin.admin-nav-mini .layui-side .layui-nav .layui-nav-item>a", function() {
		k.closeAll("tips")
	});
	h("body").on("mouseleave", ".layui-layout-admin.admin-nav-mini .layui-side", function() {
		i = false;
		setTimeout(function() {
			if (!i) {
				m.removeNavHover()
			}
		}, 500)
	});
	h("body").on("mouseenter", ".layui-layout-admin.admin-nav-mini .layui-side .layui-nav .layui-nav-item.admin-nav-hover .layui-nav-child", function() {
		i = true
	});
	if (!layui.contextMenu) {
		h(document).off("click.ctxMenu").on("click.ctxMenu", function() {
			var q = top.window.frames;
			for (var n = 0; n < q.length; n++) {
				var o = q[n];
				try {
					o.layui.jquery(".ctxMenu").remove()
				} catch (p) {}
			}
			top.layui.jquery(".ctxMenu").remove()
		})
	}
	var g = layui.data(m.tableName);
	if (g && g.theme) {
		(g.theme == c) || layui.link(m.getThemeDir() + g.theme + ".css", g.theme)
	} else {
		if (c != m.defaultTheme) {
			layui.link(m.getThemeDir() + m.defaultTheme + ".css", m.defaultTheme)
		}
	}
	f("admin", m)
});