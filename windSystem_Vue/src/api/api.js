import axios from 'axios';
import { loginreq, req } from './axiosFun';
export const monitorList = (params) => { return req("get", "http://localhost:8080/windfieldinfos", params) };
export const monitorSave = (params) => { return req("get", "../../static/monitor.json", params) };
export const monitorDelete = (params) => { return req("get", "../../static/monitor.json", params) };


export const faultList = (params) => { return req("get", "http://localhost:8080/faultmanages", params) };
export const faultSave = (params) => { return req("post", "http://localhost:8080/faultmanages", params) };
//export const faultDelete = (params) => { return req("delete", `http://localhost:8080/faultmanages/${row.uid}`, params) };

export const maintainList = (params) => { return req("get", "http://localhost:8080/maintenances", params) };
export const maintainSave = (params) => { return req("get", "../../static/monitor.json", params) };
export const maintainDelete = (params) => { return req("get", "../../static/monitor.json", params) };

export const premaintainList = (params) => { return req("get", "http://localhost:8080/preventmaintenances", params) };
export const premaintainSave = (params) => { return req("get", "../../static/monitor.json", params) };
export const premaintainDelete = (params) => { return req("get", "../../static/monitor.json", params) };

export const basicList = (params) => { return req("get", "http://localhost:8080/basicdatas", params) };
export const basicSave = (params) => { return req("get", "../../static/monitor.json", params) };
export const basicDelete = (params) => { return req("delete", "http://localhost:8080/basicdatas/A9", params) };

export const newpartList = (params) => { return req("get", "http://localhost:8080/newpartsmanages", params) };
export const newpartSave = (params) => { return req("get", "../../static/monitor.json", params) };
export const newpartDelete = (params) => { return req("get", "../../static/monitor.json", params) };

export const oldpartList = (params) => { return req("get", "http://localhost:8080/oldpartsmanages", params) };
export const oldpartSave = (params) => { return req("get", "../../static/monitor.json", params) };
export const oldpartDelete = (params) => { return req("get", "../../static/monitor.json", params) };

export const usersList = (params) => { return req("get", "http://localhost:8080/rightmanages", params) };
export const usersSave = (params) => { return req("get", "../../static/monitor.json", params) };
export const usersDelete = (params) => { return req("get", "../../static/monitor.json", params) };

export const personList = (params) => { return req("get", "http://localhost:8080/personmanages", params) };