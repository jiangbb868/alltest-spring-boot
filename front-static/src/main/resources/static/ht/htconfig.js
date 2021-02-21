window.htconfig = {
    Color: {              
        highlight: '#C1DDF1', 
        titleBackground: '#0099CC',    
        titleIconBackground: 'white',
        headerBackground: '#F5F5F5',
        labelSelect: 'black',
        toolTipBackground: '#EAF3FA',
        widgetIconBorder: '#A6A6A6',
        widgetIconBackground: '#A6A6A6',
        headerSeparator: '#A6A6A6',
    },
    Default: {  
        accordionViewLabelColor: 'white',
        tabViewLabelColor: 'white',
        graph3dViewGridColor: [0.9, 0.9, 0.9, 1.0],
        appendToScreen: function (p){
            var K = document;
            p = p.getView ? p.getView() : p;
            if (navigator.userAgent.toLowerCase().indexOf('firefox') != -1
                && document.mozFullScreenElement) {
                var top = $(p).css('top');
                $(p).css('top', parseInt(top) - $(document).scrollTop() + 'px');
            }
            $(p).css('z-index', 10);
            (K.fullscreenElement||K.mozFullScreenElement||K.webkitFullscreenElement||K.msFullscreenElement||K.body).appendChild(p);
        },
        "zoomMax": 2,
        contextMenuLabelColor: 'rgba(0,0,0,.65)',
        contextMenuHoverBackground: '#E6F7FF',
        contextMenuHoverLabelColor: 'rgba(0,0,0,.65)',
        "toolTipLabelFont": "12px arial, sans-serif",
        "toolTipBackground": "#FFF",
        "toolTipShadowColor": "rgba(0,0,0,0.35)",
        toolTipDelay: 300,
        "showToolTip": function(e, html) {
            if ($('#topo_tooltip').length > 0) return;
            var div = $('<div id="topo_tooltip"></div>');
            div.css({
                'position': 'absolute',
                'top': e.pageY + 15 + 'px',
                'left': e.clientX + 15 + 'px',
                'z-index': 5
            });
            if (navigator.userAgent.toLowerCase().indexOf('firefox') != -1
                && document.mozFullScreenElement) {
                div.css('top', e.clientY + 15 + 'px');
            }
            div.html(html);
            var K = document;
            $(K.fullscreenElement||K.mozFullScreenElement||K.webkitFullscreenElement||K.msFullscreenElement||K.body)
                .append(div);
        },
        "hideToolTip": function(){
            $('#topo_tooltip').remove();
        }
    },
    Style:{                    
        'group.title.background': '#0099CC',
        'group.title.color': 'white',
        'note.color': 'white',
        'note.background': '#3498DB',
        'select.color': '#1890FF',
        'select.width': 0.9,
        'select.padding': 5,
        'label.color': '#666',
    }
};