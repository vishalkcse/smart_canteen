package com.smartcanteen.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "preorder")
public class PreOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // customer
    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    // menu item
    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable=false)
    private MenuItem menuItem;

    @Column(nullable=false)
    private int quantity;

    @Column(nullable=false)
    private LocalDateTime pickupTime;

    @Column(nullable=false)
    private String status; // PENDING, READY, PICKED

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public MenuItem getMenuItem() { return menuItem; }
    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDateTime getPickupTime() { return pickupTime; }
    public void setPickupTime(LocalDateTime pickupTime) { this.pickupTime = pickupTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
