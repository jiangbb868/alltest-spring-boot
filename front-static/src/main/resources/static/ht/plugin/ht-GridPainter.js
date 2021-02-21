ht.graph.GridPainter = function(graphView) {
  var gv = this._gv = graphView;
};
ht.Default.def(ht.graph.GridPainter, Object, {
  draw: function(g) {
    var self = this,
      gv = self._gv,
      defaultPhysicalGap = 50,
      zoom = gv.getZoom(),
      viewRect = gv.getViewRect();
    var gap = defaultPhysicalGap * zoom;

    gap = gap / zoom;
    var x = Math.round(viewRect.x),
      y = Math.round(viewRect.y),
      offsetX = x % gap,
      offsetY = y % gap,
      x = x - offsetX - gap,
      y = y - offsetY - gap,
      width = Math.round(viewRect.width) + gap * 2,
      height = Math.round(viewRect.height) + gap * 2,
      startX = x,
      endX = x + width,
      startY = y,
      endY = y + height,
      lineWidth = 1 / zoom,
      rowIndex = 0,
      columnIndex = 0;



    g.save();
    g.lineWidth = lineWidth;

    for (i = startX; i <= endX; i += gap) {
      for (j = startY; j <= endY; j += gap) {
        drawLineV(i, j);
        drawLineH(i, j);
      }
    }

    g.restore();

    function drawLineV(x, y) {
      var gradientV,
        y1 = y - gap / 2,
        y2 = y1 + gap;
      g.beginPath();
      gradientV = g.createLinearGradient(x, y1, x, y2);
      gradientV.addColorStop("0.3", "rgba(158, 158, 158, 0.1)");
      gradientV.addColorStop("0.5", "rgba(158, 158, 158, 1.0)");
      gradientV.addColorStop("0.7", "rgba(158, 158, 158, 0.1)");
      g.strokeStyle = gradientV;
      g.moveTo(x, y1);
      g.lineTo(x, y2);
      g.stroke();
    }

    function drawLineH(x, y) {
      var gradientH,
        x1 = x - gap / 2,
        x2 = x1 + gap;
      g.beginPath();
      gradientH = g.createLinearGradient(x1, y, x2, y);
      gradientH.addColorStop("0.3", "rgba(158, 158, 158, 0.1)");
      gradientH.addColorStop("0.5", "rgba(158, 158, 158, 1.0)");
      gradientH.addColorStop("0.7", "rgba(158, 158, 158, 0.1)");
      g.strokeStyle = gradientH;
      g.moveTo(x1, y);
      g.lineTo(x2, y);
      g.stroke();
    }
  }



});
