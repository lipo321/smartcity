package com.example.demo.test;

import com.vividsolutions.jts.geom.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GxBBox extends OGeometry {
    private double minx;
    private double miny;
    private double minz;
    private double maxx;
    private double maxy;
    private double maxz;

    public GxBBox() {
        this.minx = 0.0;
        this.miny = 0.0;
        this.minz = 0.0;
        this.maxx = 0.0;
        this.maxy = 0.0;
        this.maxz = 0.0;
    }

    @Override
    public String toWKT() {
        return String.format("BBOX(%s %s %s %s %s %s)",
                String.valueOf(this.minx), String.valueOf(this.maxx),
                String.valueOf(this.miny), String.valueOf(this.maxy),
                String.valueOf(this.minz), String.valueOf(this.maxz));
    }

    @Override
    public void fromWkt(String wkt) {

    }

    public GxBBox(double minx, double maxx, double miny, double maxy, double minz, double maxz) {
        this.minx = minx;
        this.miny = miny;
        this.minz = minz;
        this.maxx = maxx;
        this.maxy = maxy;
        this.maxz = maxz;
    }

    @Override
    public String getGeometryType() {
        return null;
    }

    @Override
    public Coordinate getCoordinate() {
        return null;
    }

    @Override
    public Coordinate[] getCoordinates() {
        return new Coordinate[0];
    }

    @Override
    public int getNumPoints() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getDimension() {
        return 0;
    }

    @Override
    public Geometry getBoundary() {
        return null;
    }

    @Override
    public int getBoundaryDimension() {
        return 0;
    }

    @Override
    public Geometry reverse() {
        return null;
    }

    @Override
    public boolean equalsExact(Geometry geometry, double v) {
        return false;
    }

    @Override
    public void apply(CoordinateFilter coordinateFilter) {

    }

    @Override
    public void apply(CoordinateSequenceFilter coordinateSequenceFilter) {

    }

    @Override
    public void apply(GeometryFilter geometryFilter) {

    }

    @Override
    public void apply(GeometryComponentFilter geometryComponentFilter) {

    }

    @Override
    public void normalize() {

    }

    @Override
    protected Envelope computeEnvelopeInternal() {
        return null;
    }

    @Override
    protected int compareToSameClass(Object o) {
        return 0;
    }

    @Override
    protected int compareToSameClass(Object o, CoordinateSequenceComparator coordinateSequenceComparator) {
        return 0;
    }
}
