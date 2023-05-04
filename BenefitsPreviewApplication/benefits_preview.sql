--
-- File generated with SQLiteStudio v3.4.4 on Wed May 3 17:04:07 2023
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: benefits
CREATE TABLE IF NOT EXISTS benefits (benefitId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, benefitCost DECIMAL (20) NOT NULL, benefitType NVARCHAR (50) NOT NULL, companyId FOREIGN_KEY REFERENCES company (companyId) NOT NULL);
INSERT INTO benefits (benefitId, benefitCost, benefitType, companyId) VALUES (1, 'SPOUSE_BENEFIT', '$500.00', 1);
INSERT INTO benefits (benefitId, benefitCost, benefitType, companyId) VALUES (2, 'CHILD_BENEFIT', '$500.00', 1);
INSERT INTO benefits (benefitId, benefitCost, benefitType, companyId) VALUES (3, 'SELF_EMPL_BENEFIT', '$1,000.00', 1);
INSERT INTO benefits (benefitId, benefitCost, benefitType, companyId) VALUES (4, 'SPOUSE_BENEFIT', '$500.00', 2);
INSERT INTO benefits (benefitId, benefitCost, benefitType, companyId) VALUES (5, 'CHILD_BENEFIT', '$500.00', 2);
INSERT INTO benefits (benefitId, benefitCost, benefitType, companyId) VALUES (6, 'SELF_EMPL_BENEFIT', '$1,000.00', 2);

-- Table: company
CREATE TABLE IF NOT EXISTS company (companyId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, companyName VARCHAR(30) NOT NULL);
INSERT INTO company (companyId, companyName) VALUES (1, 'ABC');
INSERT INTO company (companyId, companyName) VALUES (2, 'XYZ');

-- Table: dependent
CREATE TABLE IF NOT EXISTS dependent (DependentId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, LastName NVARCHAR (20) NOT NULL, FirstName NVARCHAR (20) NOT NULL, DependentType NVARCHAR (20) NOT NULL, EmployeeID INTEGER NOT NULL REFERENCES emp (EmployeeId));
INSERT INTO dependent (DependentId, LastName, FirstName, DependentType, EmployeeID) VALUES (1, 'JR', 'DOE', 'CHILD', 1);
INSERT INTO dependent (DependentId, LastName, FirstName, DependentType, EmployeeID) VALUES (2, 'JR', 'SMITH', 'CHILD', 2);
INSERT INTO dependent (DependentId, LastName, FirstName, DependentType, EmployeeID) VALUES (3, 'EDDIE', 'MURPHY', 'SPOUSE', 3);

-- Table: emp
CREATE TABLE IF NOT EXISTS emp (EmployeeId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, LastName NVARCHAR (20) NOT NULL, FirstName NVARCHAR (20) NOT NULL, Title NVARCHAR (30) NOT NULL, companyId INTEGER REFERENCES company (companyId) NOT NULL);
INSERT INTO emp (EmployeeId, LastName, FirstName, Title, companyId) VALUES (1, 'JOHN', 'DOE', '$2,000.00', 1);
INSERT INTO emp (EmployeeId, LastName, FirstName, Title, companyId) VALUES (2, 'JAMES', 'SMITH', '$2,000.00', 2);
INSERT INTO emp (EmployeeId, LastName, FirstName, Title, companyId) VALUES (3, 'JANE', 'MURPHY', '$2000.00', 1);

-- View: emp_benefits
CREATE VIEW IF NOT EXISTS emp_benefits as 
select e.employeeId, e.companyId, d.dependentId, b.benefitCost from emp e , dependent d, benefits b 
where e.companyId=b.companyId and e.employeeId=d.dependentId;

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
