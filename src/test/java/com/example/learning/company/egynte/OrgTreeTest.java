package com.example.learning.company.egynte;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrgTreeTest {
    
    private Employee buildSampleTree() {
        Employee ceo = new Employee("ceo", "CEO");
        Employee vp1 = new Employee("vp1", "VP1");
        Employee vp2 = new Employee("vp2", "VP2");
        Employee m1 = new Employee("m1", "M1");
        Employee m2 = new Employee("m2", "M2");
        Employee e1 = new Employee("e1", "E1");
        Employee e2 = new Employee("e2", "E2");
        
        ceo.directReports = Arrays.asList(vp1, vp2);
        vp1.directReports = Arrays.asList(m1, m2);
        m1.directReports = Arrays.asList(e1, e2);
        
        return ceo;
    }
    
    @Test
    public void testLCM_SameParent() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        Employee lcm = tree.findLCM(root, "e1", "e2");
        assertNotNull(lcm);
        assertEquals("m1", lcm.id);
    }
    
    @Test
    public void testLCM_DifferentBranches() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        Employee lcm = tree.findLCM(root, "e1", "m2");
        assertNotNull(lcm);
        assertEquals("vp1", lcm.id);
    }
    
    @Test
    public void testLCM_RootIsLCM() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        Employee lcm = tree.findLCM(root, "vp1", "vp2");
        assertNotNull(lcm);
        assertEquals("ceo", lcm.id);
    }
    
    @Test
    public void testLCM_OneIsAncestorOfOther() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        Employee lcm = tree.findLCM(root, "vp1", "e1");
        assertNotNull(lcm);
        assertEquals("vp1", lcm.id);
    }
    
    @Test
    public void testLCM_SameEmployee() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        Employee lcm = tree.findLCM(root, "e1", "e1");
        assertNotNull(lcm);
        assertEquals("e1", lcm.id);
    }
    
    @Test
    public void testLCM_EmployeeNotFound() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        Employee lcm = tree.findLCM(root, "e1", "unknown");
        assertNull(lcm);
    }
    
    @Test
    public void testDistance_DirectPath() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        int dist = tree.findDistance(root, "ceo", "e1");
        assertEquals(3, dist); // CEO -> VP1 -> M1 -> E1
    }
    
    @Test
    public void testDistance_AcrossBranches() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        int dist = tree.findDistance(root, "e1", "m2");
        assertEquals(3, dist); // E1 -> M1 -> VP1 -> M2
    }
    
    @Test
    public void testDistance_SameEmployee() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        int dist = tree.findDistance(root, "e1", "e1");
        assertEquals(0, dist);
    }
    
    @Test
    public void testDistance_NotFound() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        int dist = tree.findDistance(root, "e1", "unknown");
        assertEquals(-1, dist);
    }
    
    @Test
    public void testEmployeesAtDistanceK_Zero() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        List<String> result = tree.employeesAtDistanceK(root, "ceo", 0);
        assertEquals(1, result.size());
        assertTrue(result.contains("ceo"));
    }
    
    @Test
    public void testEmployeesAtDistanceK_One() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        List<String> result = tree.employeesAtDistanceK(root, "ceo", 1);
        assertEquals(2, result.size());
        assertTrue(result.contains("vp1"));
        assertTrue(result.contains("vp2"));
    }
    
    @Test
    public void testEmployeesAtDistanceK_FromMiddle() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        List<String> result = tree.employeesAtDistanceK(root, "vp1", 1);
        assertEquals(3, result.size());
        assertTrue(result.contains("ceo")); // parent
        assertTrue(result.contains("m1"));  // child
        assertTrue(result.contains("m2"));  // child
    }
    
    @Test
    public void testEmployeesAtDistanceK_NoEmployees() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        List<String> result = tree.employeesAtDistanceK(root, "ceo", 10);
        assertTrue(result.isEmpty());
    }
    
    @Test
    public void testDiameter_SingleNode() {
        Employee root = new Employee("ceo", "CEO");
        OrgTree tree = new OrgTree();
        
        int diameter = tree.findDiameter(root);
        assertEquals(0, diameter);
    }
    
    @Test
    public void testDiameter_LinearTree() {
        Employee e1 = new Employee("e1", "E1");
        Employee e2 = new Employee("e2", "E2");
        Employee e3 = new Employee("e3", "E3");
        Employee e4 = new Employee("e4", "E4");
        
        e1.directReports = Arrays.asList(e2);
        e2.directReports = Arrays.asList(e3);
        e3.directReports = Arrays.asList(e4);
        
        OrgTree tree = new OrgTree();
        int diameter = tree.findDiameter(e1);
        assertEquals(3, diameter);
    }
    
    @Test
    public void testDiameter_BalancedTree() {
        Employee root = buildSampleTree();
        OrgTree tree = new OrgTree();
        
        int diameter = tree.findDiameter(root);
        // Longest path: E1 -> M1 -> VP1 -> M2 OR E2 -> M1 -> VP1 -> M2
        assertEquals(3, diameter);
    }
    
    @Test
    public void testDiameter_ComplexTree() {
        // Build a more complex tree
        Employee ceo = new Employee("ceo", "CEO");
        Employee vp1 = new Employee("vp1", "VP1");
        Employee vp2 = new Employee("vp2", "VP2");
        Employee m1 = new Employee("m1", "M1");
        Employee m2 = new Employee("m2", "M2");
        Employee m3 = new Employee("m3", "M3");
        Employee e1 = new Employee("e1", "E1");
        Employee e2 = new Employee("e2", "E2");
        Employee e3 = new Employee("e3", "E3");
        Employee e4 = new Employee("e4", "E4");
        
        ceo.directReports = Arrays.asList(vp1, vp2);
        vp1.directReports = Arrays.asList(m1);
        vp2.directReports = Arrays.asList(m2, m3);
        m1.directReports = Arrays.asList(e1, e2);
        m3.directReports = Arrays.asList(e3);
        e3.directReports = Arrays.asList(e4);
        
        OrgTree tree = new OrgTree();
        int diameter = tree.findDiameter(ceo);
        // Longest path: E1 -> M1 -> VP1 -> CEO -> VP2 -> M3 -> E3 -> E4
        assertEquals(7, diameter);
    }
}
