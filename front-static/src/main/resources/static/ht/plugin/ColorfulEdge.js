ht.ColorfulEdgeUI = function(graphView, data) {
    ht.ColorfulEdgeUI.superClass.constructor.call(this, graphView, data);
}
ht.Default.def('ht.ColorfulEdgeUI', ht.Default.getInternal().ui().EdgeUI, {
    _80o: function(g) {
        var self = this,
            getStyle = self.s,
            info = self._78o,
            width = info.width,
            edge = self._data;
        selectWidth = info.selectWidth,
            borderWidth = info.borderWidth,
            color = info.color,
            sourceColor = edge.s('source.color'),
            targetColor = edge.s('target.color'),
            oldJoin = g.lineJoin,
            oldCap = g.lineCap,
            pattern = info.pattern;
        var isEmptyArray = function(array) {
            return array ? array.length === 0 : true;
        };
        var restoreDash = function(g, pattern) {
            if (!isEmptyArray(pattern) && g.setLineDash) {
                g.setLineDash([]);
                g.lineDashOffset = 0;
            }
        };

        g.lineJoin = info.join;
        g.lineCap = info.cap;

        var draw = function(customColor) {
            if (selectWidth) {
                g.strokeStyle = edge.s('select.color');
                g.lineWidth = width + (borderWidth + selectWidth) * 2;
                g.stroke();
            }

            if (borderWidth) {
                g.strokeStyle = info.borderColor;
                g.lineWidth = width + borderWidth * 2;
                g.stroke();
            }

            g.strokeStyle = customColor || color;
            g.lineWidth = width;
            g.stroke();
        }

        if (!edge.s('edge.type') && sourceColor && targetColor) {
            var sourcePoint = info._69o;
            var targetPoint = info._70o;
            var centerPoint = {
                x: (sourcePoint.x + targetPoint.x) / 2,
                y: (sourcePoint.y + targetPoint.y) / 2
            };
            info._69o = sourcePoint;
            info._70o = centerPoint;
            self.drawPath(ht.Default.getInternal().createG2(g, pattern), info);
            draw(sourceColor);

            info._69o = centerPoint;
            info._70o = targetPoint;
            self.drawPath(ht.Default.getInternal().createG2(g, pattern), info);
            draw(targetColor);

            info._69o = sourcePoint;
            info._70o = targetPoint;
        } else {
            self.drawPath(ht.Default.getInternal().createG2(g, pattern), info);
            draw();
        }
        restoreDash(g, pattern);

        if (getStyle('edge.dash')) {
            pattern = getStyle('edge.dash.pattern');
            var g2 = ht.Default.getInternal().createG2(g, pattern, self._2Q('edge.dash.offset'));
            if (g2 !== g) {
                self.drawPath(g2, info);
            }
            self._1Q(g, 'edge', getStyle('edge.dash.width') || width, pattern);
        }

        g.lineJoin = oldJoin;
        g.lineCap = oldCap;
    }
});

ht.ColorfulEdge = function(source, target) {
    ht.ColorfulEdge.superClass.constructor.call(this, source, target);
};

ht.Default.def('ht.ColorfulEdge', ht.Edge, {
    getUIClass: function() {
        return ht.ColorfulEdgeUI;
    }
});
