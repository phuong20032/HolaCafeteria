/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ngoba
 */
public class Paging {

    public Paging() {
    }
    private int nrpp;
    private int index;
    private int size;

    public Paging(int nrpp, int index, int size) {
        this.nrpp = nrpp;
        this.index = index;
        this.size = size;
    }

    private int totalPage;
    private int begin;
    private int end;
    private int pageStart, pageEnd;

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public void calc() {
        //totalPage = size/nrpp+size%nrpp==0?0:1;
        totalPage = (size + nrpp - 1) / nrpp;
        index = index < 0 ? 0 : index;
        index = index > totalPage ? totalPage - 1 : index;
        begin = index * nrpp;
        end = begin + nrpp;
        end = end >= size ? size : end;
        pageStart = index - 2;
        pageStart = pageStart < 0 ? 0 : pageStart;
        pageEnd = index + 2;
        pageEnd = pageEnd >= totalPage - 1 ? totalPage - 1 : pageEnd;
    }

    public int getNrpp() {
        return nrpp;
    }

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}
