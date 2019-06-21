/** EasyWeb iframe v3.1.1 data:2019-03-24 License By http://easyweb.vip */

layui.define(["layer", "element", "admin", "contextMenu"], function(s) {
	var d = layui.jquery;
	var r = layui.layer;
	var b = layui.element;
	var m = layui.admin;
	var t = layui.contextMenu;
	var a = ".layui-layout-admin>.layui-header";
	var o = ".layui-layout-admin>.layui-side>.layui-side-scroll";
	var j = ".layui-layout-admin>.layui-body";
	var n = j + ">.layui-tab";
	var q = j + ">.layui-body-header";
	var i = "admin-pagetabs";
	var p = "admin-side-nav";
	var k = {};
	var f = false;
	var c;
	var h = {
		pageTabs: false,
		cacheTab: true,
		openTabCtxMenu: true,
		maxTabNum: 20,
		mTabList: [],
		mTabPosition: undefined,
		loadView: function(z) {
			var x = z.menuPath;
			var w = z.menuName;
			if (!x) {
				console.error("url不能为空");
				r.msg("url不能为空", {
					icon: 2
				});
				return
			}
			if (h.pageTabs) {
				var v = false;
				d(n + ">.layui-tab-title>li").each(function() {
					if (d(this).attr("lay-id") === x) {
						v = true;
						return false
					}
				});
				if (!v) {
					if (h.mTabList.length >= h.maxTabNum) {
						r.msg("最多打开" + h.maxTabNum + "个选项卡", {
							icon: 2
						});
						m.activeNav(h.mTabPosition);
						return
					}
					f = true;
					b.tabAdd(i, {
						id: x,
						title: w ? w : "无标题",
						content: '<iframe lay-id="' + x + '" src="' + x + '" frameborder="0" class="admin-iframe"></iframe>'
					});
					if (x != c) {
						h.mTabList.push(z)
					}
					if (h.cacheTab) {
						m.putTempData("indexTabs", h.mTabList)
					}
				}
				b.tabChange(i, x)
			} else {
				var u = d(j + ">.admin-iframe");
				if (!u || u.length <= 0) {
					var y = '<div class="layui-body-header">';
					y += '      <span class="layui-body-header-title"></span>';
					y += '      <span class="layui-breadcrumb pull-right">';
					y += '         <a ew-href="' + c + '">首页</a>';
					y += "         <a><cite></cite></a>";
					y += "      </span>";
					y += "   </div>";
					y += '   <div style="-webkit-overflow-scrolling: touch;">';
					y += '      <iframe lay-id="' + x + '" src="' + x + '" frameborder="0" class="admin-iframe"></iframe>';
					y += "   </div>";
					d(j).html(y);
					if (x != c) {
						h.setTabTitle(w)
					}
					b.render("breadcrumb")
				} else {
					u.attr("lay-id", x);
					u.attr("src", x);
					h.setTabTitle(w)
				}
				m.activeNav(x);
				h.mTabList.splice(0, h.mTabList.length);
				if (x != c) {
					h.mTabList.push(z);
					h.mTabPosition = x
				} else {
					h.mTabPosition = undefined
				}
				if (h.cacheTab) {
					m.putTempData("indexTabs", h.mTabList);
					m.putTempData("tabPosition", h.mTabPosition)
				}
			}
			if (m.getPageWidth() <= 750) {
				m.flexible(true)
			}
		},
		loadHome: function(w) {
			c = w.menuPath;
			var x = m.getTempData("indexTabs");
			var u = m.getTempData("tabPosition");
			var v = (w.loadSetting == undefined ? true : w.loadSetting);
			h.loadView({
				menuPath: c,
				menuName: w.menuName
			});
			if (!h.pageTabs) {
				m.activeNav(w.menuPath)
			}
			if (v) {
				h.loadSettings(x, u)
			}
		},
		openTab: function(w) {
			var u = w.url;
			var v = w.title;
			if (w.end) {
				k[u] = w.end
			}
			h.loadView({
				menuPath: u,
				menuName: v
			})
		},
		closeTab: function(u) {
			b.tabDelete(i, u)
		},
		loadSettings: function(z, y) {
			if (h.cacheTab) {
				var A = z;
				var w = y;
				if (A) {
					var v = -1;
					for (var x = 0; x < A.length; x++) {
						if (h.pageTabs) {
							h.loadView(A[x])
						}
						if (A[x].menuPath == w) {
							v = x
						}
					}
					if (v != -1) {
						setTimeout(function() {
							h.loadView(A[v]);
							if (!h.pageTabs) {
								m.activeNav(w)
							}
						}, 150)
					}
				}
			}
			var u = layui.data(m.tableName);
			if (u) {
				if (u.openFooter != undefined && u.openFooter == false) {
					d("body.layui-layout-body").addClass("close-footer")
				}
				if (u.tabAutoRefresh) {
					d(n).attr("lay-autoRefresh", "true")
				}
			}
		},
		setTabCache: function(u) {
			layui.data(m.tableName, {
				key: "cacheTab",
				value: u
			});
			h.cacheTab = u;
			if (u) {
				m.putTempData("indexTabs", h.mTabList);
				m.putTempData("tabPosition", h.mTabPosition)
			} else {
				m.putTempData("indexTabs", []);
				m.putTempData("tabPosition", undefined)
			}
		},
		clearTabCache: function() {
			m.putTempData("indexTabs", undefined)
		},
		setTabTitle: function(u) {
			if (!h.pageTabs) {
				if (u) {
					d(q).addClass("show");
					var v = d(q + ">.layui-body-header-title");
					v.text(u);
					v.next(".layui-breadcrumb").find("cite").last().text(u)
				} else {
					d(q).removeClass("show")
				}
			}
		},
		setTabTitleHtml: function(u) {
			if (!h.pageTabs) {
				if (u) {
					d(q).addClass("show");
					d(q).html(u)
				} else {
					d(q).removeClass("show")
				}
			}
		},
		closeTabCache: function() {
			console.warn("closeTabCache() has been deprecated, please use clearTabCache().");
			h.clearTabCache()
		},
		loadSetting: function() {
			console.warn("loadSetting() has been deprecated.")
		}
	};
	var l = layui.data(m.tableName);
	if (l) {
		if (l.openTab != undefined) {
			h.pageTabs = l.openTab
		}
		if (l.cacheTab != undefined) {
			h.cacheTab = l.cacheTab
		}
	}
	var g = ".layui-layout-admin .site-mobile-shade";
	if (d(g).length <= 0) {
		d(".layui-layout-admin").append('<div class="site-mobile-shade"></div>')
	}
	d(g).click(function() {
		m.flexible(true)
	});
	if (h.pageTabs && d(n).length <= 0) {
		var e = '<div class="layui-tab" lay-allowClose="true" lay-filter="admin-pagetabs">';
		e += '       <ul class="layui-tab-title"></ul>';
		e += '      <div class="layui-tab-content"></div>';
		e += "   </div>";
		e += '   <div class="layui-icon admin-tabs-control layui-icon-prev" ew-event="leftPage"></div>';
		e += '   <div class="layui-icon admin-tabs-control layui-icon-next" ew-event="rightPage"></div>';
		e += '   <div class="layui-icon admin-tabs-control layui-icon-down">';
		e += '      <ul class="layui-nav admin-tabs-select" lay-filter="admin-pagetabs-nav">';
		e += '         <li class="layui-nav-item" lay-unselect>';
		e += "            <a></a>";
		e += '            <dl class="layui-nav-child layui-anim-fadein">';
		e += '               <dd ew-event="closeThisTabs" lay-unselect><a>关闭当前标签页</a></dd>';
		e += '               <dd ew-event="closeOtherTabs" lay-unselect><a>关闭其它标签页</a></dd>';
		e += '               <dd ew-event="closeAllTabs" lay-unselect><a>关闭全部标签页</a></dd>';
		e += "            </dl>";
		e += "         </li>";
		e += "      </ul>";
		e += "   </div>";
		d(j).html(e);
		b.render("nav")
	}
	b.on("nav(" + p + ")", function(x) {
		var w = d(x);
		var u = w.attr("lay-href");
		var y = w.attr("lay-id");
		if (!y) {
			y = u
		}
		if (u && u != "javascript:;") {
			var v = w.text().replace(/(^\s*)|(\s*$)/g, "");
			h.loadView({
				menuId: y,
				menuPath: u,
				menuName: v
			})
		} else {
			m.setNavHoverCss(w.parentsUntil(".layui-nav-item").parent().children().eq(0))
		}
		if ("true" == (d(o + ">.layui-nav-tree").attr("lay-accordion"))) {
			if ((w.parent().hasClass("layui-nav-itemed")) || (w.parent().hasClass("layui-this"))) {
				d(o + ">.layui-nav .layui-nav-itemed").not(w.parents(".layui-nav-child").parent()).removeClass("layui-nav-itemed");
				w.parent().addClass("layui-nav-itemed")
			}
			w.trigger("mouseenter")
		}
	});
	b.on("tab(" + i + ")", function(w) {
		var v = d(this).attr("lay-id");
		if (v != c) {
			h.mTabPosition = v
		} else {
			h.mTabPosition = undefined
		}
		if (h.cacheTab) {
			m.putTempData("tabPosition", h.mTabPosition)
		}
		m.rollPage("auto");
		m.activeNav(v);
		var x = d(n + ">.layui-tab-content>.layui-tab-item.layui-show .admin-iframe")[0];
		if (x) {
			x.style.height = "99%";
			x.scrollWidth;
			x.style.height = "100%"
		}
		x.focus();
		var u = d(n).attr("lay-autoRefresh");
		if (u === "true" && !f) {
			m.refresh(v)
		}
		f = false
	});
	b.on("tabDelete(" + i + ")", function(w) {
		var u = h.mTabList[w.index - 1];
		if (u) {
			var v = u.menuPath;
			h.mTabList.splice(w.index - 1, 1);
			if (h.cacheTab) {
				m.putTempData("indexTabs", h.mTabList)
			}
			if (k[v]) {
				k[v].call()
			}
		}
		if (d(n + ">.layui-tab-title>li.layui-this").length <= 0) {
			d(n + ">.layui-tab-title>li:last").trigger("click")
		}
	});
	d("body").off("click.navMore").on("click.navMore", "[nav-bind]", function() {
		var u = d(this).attr("nav-bind");
		d('ul[lay-filter="' + p + '"]').addClass("layui-hide");
		d('ul[nav-id="' + u + '"]').removeClass("layui-hide");
		if (m.getPageWidth() <= 750) {
			m.flexible(false)
		}
		d(a + ">.layui-nav .layui-nav-item").removeClass("layui-this");
		d(this).parent(".layui-nav-item").addClass("layui-this")
	});
	if (h.openTabCtxMenu && h.pageTabs) {
		d(n + ">.layui-tab-title").off("contextmenu.tab").on("contextmenu.tab", "li", function(v) {
			var u = d(this).attr("lay-id");
			t.show([{
				icon: "layui-icon layui-icon-refresh",
				name: "刷新当前",
				click: function() {
					b.tabChange(i, u);
					var w = d(n).attr("lay-autoRefresh");
					if (!w || w !== "true") {
						m.refresh(u)
					}
				}
			}, {
				icon: "layui-icon layui-icon-close-fill ctx-ic-lg",
				name: "关闭当前",
				click: function() {
					m.closeThisTabs(u)
				}
			}, {
				icon: "layui-icon layui-icon-unlink",
				name: "关闭其他",
				click: function() {
					m.closeOtherTabs(u)
				}
			}, {
				icon: "layui-icon layui-icon-close ctx-ic-lg",
				name: "关闭全部",
				click: function() {
					m.closeAllTabs()
				}
			}], v.clientX, v.clientY);
			return false
		})
	}
	s("index", h)
});