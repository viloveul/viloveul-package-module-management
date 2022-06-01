INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy01','USER','USER',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy02','SCOPE','SCOPE',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy03','CREDENTIAL','CREDENTIAL',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy04','ASSIGNMENT','ASSIGNMENT',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy05','GROUP','GROUP',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy06','NOTIFICATION','NOTIFICATION',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy07','OPERATION','OPERATION',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy08','PRIVILEGE','PRIVILEGE',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy09','RESOURCE','RESOURCE',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy10','MEDIA','MEDIA',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy11','OWNER','OWNER',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy12','PERMISSION','PERMISSION',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy13','SETTING','SETTING',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy14','API','API',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy15','MAIL','MAIL',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_resource (id,"identity",name,status,created_at,created_by) VALUES ('res00000-0000-0000-000-d90b3dummy16','JOB','JOB',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;

INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy01','DELETE','DELETE',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy02','DETAIL','DETAIL',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy03','CREATE','CREATE',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy04','UPDATE','UPDATE',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy05','SEARCH','SEARCH',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy06','IMPORT','IMPORT',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy07','EXPORT','EXPORT',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy08','ACTIVATION','ACTIVATION',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy09','VERIFICATION','VERIFICATION',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy10','SEND','SEND',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_operation (id,"identity",name,status,created_at,created_by) VALUES ('opr00000-0000-0000-000-d90b3dummy11','RESEND','RESEND',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;

INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy01','ROLE','ADMINISTRATOR','Administrator',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02','ROLE','DEFAULT','Default',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;

INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy01','TASK','USER-ADMINISTRATING','User Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy02','TASK','SCOPE-ADMINISTRATING','Scope Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy03','TASK','CREDENTIAL-ADMINISTRATING','Credential Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy04','TASK','ASSIGNMENT-ADMINISTRATING','Assignment Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy05','TASK','GROUP-ADMINISTRATING','Group Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy06','TASK','NOTIFICATION-ADMINISTRATING','Notification Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy07','TASK','OPERATION-ADMINISTRATING','Operation Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy08','TASK','PRIVILEGE-ADMINISTRATING','Privilege Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_privilege (id,type,"identity",name,status,created_at,created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy09','TASK','RESOURCE-ADMINISTRATING','Resource Administrating',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;

INSERT INTO tbl_group (id,"identity",name,path,status,created_at,created_by) VALUES ('group000-0000-0000-adm-d90b3dummy01','DEFAULT','DEFAULT','/DEFAULT',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_group (id,"identity",name,path,status,created_at,created_by) VALUES ('group000-0000-0000-adm-d90b3dummy02','SAMPLE-LVL-1','SAMPLE LVL 1','/SAMPLE-LVL-1',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_group (id,id_parent,"identity",name,path,status,created_at,created_by) VALUES ('group000-0000-0000-adm-d90b3dummy03','group000-0000-0000-adm-d90b3dummy02','SAMPLE-LVL-2','SAMPLE LVL 2','/SAMPLE-LVL-1/SAMPLE-LVL-2',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_group (id,id_parent,"identity",name,path,status,created_at,created_by) VALUES ('group000-0000-0000-adm-d90b3dummy04','group000-0000-0000-adm-d90b3dummy03','SAMPLE-LVL-3','SAMPLE LVL 3','/SAMPLE-LVL-1/SAMPLE-LVL-2/SAMPLE-LVL-3',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;

INSERT INTO tbl_user (id,id_group,type,email,fullname,username,status,created_at,created_by) VALUES ('fajrulaz-0000-0000-adm-d90b3dummy01','group000-0000-0000-adm-d90b3dummy01','ADMIN','fajrulaz@gmail.com','Administrator','admin',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_user (id,id_group,type,email,fullname,username,status,created_at,created_by) VALUES ('fajrulaz-0000-0000-adm-d90b3dummy02','group000-0000-0000-adm-d90b3dummy01','USER','fajrulaz@gmail.com','User','user',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;

--value = In1P@ssw0rdNy4!!!
INSERT INTO tbl_credential (id,id_user,"value",type,description,status,created_at,created_by) VALUES ('pass0000-0000-0000-adm-d90b3dummy01','fajrulaz-0000-0000-adm-d90b3dummy01','$2a$10$wcsstXpSGqkYaSQUOTFmweKGvkeqZJNsPRSOUrzurKoxePLszEw/K','PASSWORD','PASSWORD FROM DUMMY',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;
INSERT INTO tbl_credential (id,id_user,"value",type,description,status,created_at,created_by) VALUES ('pass0000-0000-0000-adm-d90b3dummy02','fajrulaz-0000-0000-adm-d90b3dummy02','$2a$10$wcsstXpSGqkYaSQUOTFmweKGvkeqZJNsPRSOUrzurKoxePLszEw/K','PASSWORD','PASSWORD FROM DUMMY',true,CURRENT_DATE,'system:dummy') ON CONFLICT(id) DO NOTHING;


-- PIVOT RELATIONSHIP

INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy01') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy02') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy03') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy04') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy05') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy06') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy07') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy08') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;
INSERT INTO tbl_privilege_agregate (id_privilege,id_agregate) VALUES ('role0000-0000-0000-adm-d90b3dummy01','task0000-0000-0000-adm-d90b3dummy09') ON CONFLICT(id_privilege,id_agregate) DO NOTHING;

-- REGISTER

-- USER ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy01', 'res00000-0000-0000-000-d90b3dummy01', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy01', 'res00000-0000-0000-000-d90b3dummy01', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy01', 'res00000-0000-0000-000-d90b3dummy01', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy01', 'res00000-0000-0000-000-d90b3dummy01', 'opr00000-0000-0000-000-d90b3dummy04', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy01', 'res00000-0000-0000-000-d90b3dummy01', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy01', 'res00000-0000-0000-000-d90b3dummy01', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- SCOPE ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy02', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy02', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy02', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy02', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy02', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- CREDENTIAL ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy03', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy03', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy03', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy03', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy03', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- ASSIGNMENT ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy04', 'res00000-0000-0000-000-d90b3dummy04', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy04', 'res00000-0000-0000-000-d90b3dummy04', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy04', 'res00000-0000-0000-000-d90b3dummy04', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy04', 'res00000-0000-0000-000-d90b3dummy04', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy04', 'res00000-0000-0000-000-d90b3dummy04', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- GROUP ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy05', 'res00000-0000-0000-000-d90b3dummy05', 'opr00000-0000-0000-000-d90b3dummy04', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy05', 'res00000-0000-0000-000-d90b3dummy05', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy05', 'res00000-0000-0000-000-d90b3dummy05', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy05', 'res00000-0000-0000-000-d90b3dummy05', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy05', 'res00000-0000-0000-000-d90b3dummy05', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy05', 'res00000-0000-0000-000-d90b3dummy05', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- NOTIFICATION ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy06', 'res00000-0000-0000-000-d90b3dummy06', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy06', 'res00000-0000-0000-000-d90b3dummy06', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- OPERATION ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy07', 'res00000-0000-0000-000-d90b3dummy07', 'opr00000-0000-0000-000-d90b3dummy04', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy07', 'res00000-0000-0000-000-d90b3dummy07', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy07', 'res00000-0000-0000-000-d90b3dummy07', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy07', 'res00000-0000-0000-000-d90b3dummy07', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy07', 'res00000-0000-0000-000-d90b3dummy07', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy07', 'res00000-0000-0000-000-d90b3dummy07', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- PRIVILEGE ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy08', 'res00000-0000-0000-000-d90b3dummy08', 'opr00000-0000-0000-000-d90b3dummy04', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy08', 'res00000-0000-0000-000-d90b3dummy08', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy08', 'res00000-0000-0000-000-d90b3dummy08', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy08', 'res00000-0000-0000-000-d90b3dummy08', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy08', 'res00000-0000-0000-000-d90b3dummy08', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy08', 'res00000-0000-0000-000-d90b3dummy08', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- RESOURCE ADMINISTRATING
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy09', 'res00000-0000-0000-000-d90b3dummy09', 'opr00000-0000-0000-000-d90b3dummy04', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy09', 'res00000-0000-0000-000-d90b3dummy09', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy09', 'res00000-0000-0000-000-d90b3dummy09', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy09', 'res00000-0000-0000-000-d90b3dummy09', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy09', 'res00000-0000-0000-000-d90b3dummy09', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('task0000-0000-0000-adm-d90b3dummy09', 'res00000-0000-0000-000-d90b3dummy09', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;

-- DEFAULT PRIVILEGE SCOPE
-- user
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy01', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
-- credential
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy03', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy08', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy03', 'opr00000-0000-0000-000-d90b3dummy01', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
-- group
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy05', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
-- notfication
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy06', 'opr00000-0000-0000-000-d90b3dummy02', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy06', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
-- privilege
INSERT INTO tbl_scope (id_privilege, id_resource, id_operation, status, created_at, created_by) VALUES ('role0000-0000-0000-adm-d90b3dummy02', 'res00000-0000-0000-000-d90b3dummy08', 'opr00000-0000-0000-000-d90b3dummy05', true, CURRENT_DATE, 'system:dummy') ON CONFLICT(id_privilege,id_resource,id_operation) DO NOTHING;
