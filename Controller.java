package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Controller implements Initializable {
	Dashboard dashboard;
	
	@FXML
    private TreeView<FarmItem> farmItemsTreeView;
	
	@FXML
    private ListView<ListViewCommands> farmItemCommands;
	
	@FXML
    private AnchorPane visualizationArea;
	
	public Controller() {
		// Dashboard is a singleton class
		this.dashboard = Dashboard.getInstance();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dashboard.initializeFarmItemContainer();
		farmItemsTreeView.setRoot(this.dashboard.getFarmItemsDisplayList());
		dashboard.setVisualization(visualizationArea);
	}
	
	@FXML
    void selectTreeViewItem(MouseEvent event) {
		if(farmItemsTreeView.getSelectionModel().getSelectedItem() != null) {
			FarmItem selectedFarmItem = farmItemsTreeView.getSelectionModel().getSelectedItem().getValue();
			if(selectedFarmItem != null) {
				farmItemCommands.setItems(this.dashboard.getFarmItemCommands(selectedFarmItem));
			}
		}
    }
	
	public void onListViewItemClick() {
		ListViewCommands selectedCommand = farmItemCommands.getSelectionModel().getSelectedItem();
		if(selectedCommand != null) {
			String commandName = selectedCommand.getCommandName();
			switch(commandName) {
			case "Add_Item_Container":
				FarmItem parentItemContainer = selectedCommand.getItem();
				FarmItem childItem = null;
				if(parentItemContainer instanceof ItemContainer) {
					childItem =  new ItemContainer();
					childItem.setName(openDialog("Add a Name to the Item Container", ""));
					
					childItem.setPrice(Float.parseFloat(openDialog("Set price to the Item Container", "")));
					
					childItem.setWidth(Float.parseFloat(openDialog("Set width to the Item Container", "")));
					
					childItem.setHeight(Float.parseFloat(openDialog("Set height to the Item Container", "")));
					
					childItem.setxCoordinate(Float.parseFloat(openDialog("Set XCoordinate", "")));
					
					childItem.setyCoordinate(Float.parseFloat(openDialog("Set YCoordinate","")));
				}
				if(childItem != null) {
					parentItemContainer.addItem(childItem);
				}
				break;
			case "Delete_Item_Container":
				dashboard.getRootItem().removeItem(selectedCommand.getItem());
				break;
			case "Delete_Item":
				dashboard.getRootItem().removeItem(selectedCommand.getItem());
				break;
			case "Add_Item":
				FarmItem parentItemContainer1 = selectedCommand.getItem();
				FarmItem childItem1 = null;
				if(parentItemContainer1 instanceof ItemContainer) {
					childItem1 =  new Item();
					childItem1.setName(openDialog("Add a Name to the Item", ""));
					
					childItem1.setPrice(Float.parseFloat(openDialog("Set price to the Item", "")));
					
					childItem1.setWidth(Float.parseFloat(openDialog("Set width to the Item", "")));
					
					childItem1.setHeight(Float.parseFloat(openDialog("Set height to the Item", "")));
					
					childItem1.setxCoordinate(Float.parseFloat(openDialog("Set XCoordinate", "")));
					
					childItem1.setyCoordinate(Float.parseFloat(openDialog("Set YCoordinate","")));
				}
				if(childItem1 != null) {
					parentItemContainer1.addItem(childItem1);
				}
				break;
			case "Change_X_Coordinate":
				Float newXCoordinate = Float.parseFloat(openDialog("Change X Coordinate of the FarmItem", Float.toString(selectedCommand.getItem().getxCoordinate())));
				selectedCommand.getItem().setxCoordinate(newXCoordinate);
				break;
			case "Change_Y_Coordinate":
				Float newYCoordinate = Float.parseFloat(openDialog("Change Y Coordinate of the FarmItem", Float.toString(selectedCommand.getItem().getyCoordinate())));
				selectedCommand.getItem().setyCoordinate(newYCoordinate);
				break;
			case "Change_Width":
				Float newWidth = Float.parseFloat(openDialog("Change Width of the FarmItem", Float.toString(selectedCommand.getItem().getWidth())));
				selectedCommand.getItem().setWidth(newWidth);
				break;
			case "Change_Height":
				Float newHeight = Float.parseFloat(openDialog("Change Height of the FarmItem", Float.toString(selectedCommand.getItem().getHeight())));
				selectedCommand.getItem().setHeight(newHeight);
				break;
			case "Change_Name":
				String newName = openDialog("Change Name of the Item", selectedCommand.getItem().getName());
				selectedCommand.getItem().setName(newName);
				break;
			case "Change_Price":
				Float newPrice = Float.parseFloat(openDialog("Change Price of the FarmItem", Float.toString(selectedCommand.getItem().getPrice())));
				selectedCommand.getItem().setPrice(newPrice);
				break;
				default:
					System.out.println("Name of the command not found");
			}
			
			farmItemsTreeView.setRoot(this.dashboard.getFarmItemsDisplayList());
			
			// Update the visual plane
			dashboard.setVisualization(visualizationArea);
		}
	}

	
	public void onGoToItemBtnClick() {
		if(farmItemsTreeView.getSelectionModel().getSelectedItem() != null) {
			FarmItem farmItem = farmItemsTreeView.getSelectionModel().getSelectedItem().getValue();
			if(farmItem != null) {
				if(!(farmItem instanceof ItemContainer && ((ItemContainer) farmItem).getIsRootItem())) {
					// Create a TranslateTransition
				    TranslateTransition transition = new TranslateTransition();
				    transition.setDuration(Duration.seconds(2));  // Adjust duration as needed
				    transition.setNode(this.dashboard.getDrone());

				    // Set the translation target
				    transition.setToX(farmItem.getxCoordinate() - this.dashboard.getDrone().getLayoutX() + 5);  // Relative distance in X
				    transition.setToY(farmItem.getyCoordinate() - this.dashboard.getDrone().getLayoutY() - 5);  // Relative distance in Y

				    // Play the transition
				    transition.play();
				}
			}
		}
	}
	
	public void onGoHomeBtnClick() {
		// Create a TranslateTransition
	    TranslateTransition transition = new TranslateTransition();
	    transition.setDuration(Duration.seconds(2));  // Adjust duration as needed
	    transition.setNode(this.dashboard.getDrone());

	    // Set the translation target
	    transition.setToX(320 - this.dashboard.getDrone().getLayoutX());  // Relative distance in X
	    transition.setToY(40 - this.dashboard.getDrone().getLayoutY());  // Relative distance in Y

	    // Play the transition
	    transition.play();
	}
	
	public void onScanFarmBtnClick() {
	    List<TranslateTransition> transitions = createZigZagTransitions(visualizationArea, this.dashboard);
	    playNextZigZagTransition(transitions, 0);
	}

	private String openDialog(String headerText, String initialvalue) {
        TextInputDialog dialog = new TextInputDialog(initialvalue);
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText(headerText);
        dialog.setContentText("Data:");

        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(inputText -> {});
        return result.get();
    }
	
	private static List<TranslateTransition> createZigZagTransitions(AnchorPane visualizationArea, Dashboard dashboard) {
	    List<TranslateTransition> transitions = new ArrayList<>();
	    ImageView drone = dashboard.getDrone();
	    visualizationArea.getChildren().remove(drone);

	    // Set the starting position of the drone
	    drone = new ImageView(new Image("drone.png"));
	    dashboard.setDrone(drone);
	    drone.setLayoutX(0);
	    drone.setLayoutY(40);
	    visualizationArea.getChildren().add(drone);

	    // Initial coordinates for the drone's path
	    double xStart = 40;
	    double yStart = drone.getLayoutY();
	    double xEnd = visualizationArea.getWidth() - drone.getLayoutX() - 40;
	    double yIncrement = 100;
	    boolean moveRight = true;

	    while (yStart < visualizationArea.getHeight() - yIncrement) {
	        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), drone);
	        
	        if (moveRight) {
	            transition.setToX(xEnd - xStart);  
	        } else {
	            transition.setToX(xStart - xEnd + 600);  
	        }
	        
	        transition.setToY(yStart);  
	        
	        transitions.add(transition);

	        yStart += yIncrement;
	        moveRight = !moveRight;
	    }

	    TranslateTransition finalTransition = new TranslateTransition(Duration.seconds(2), drone);
	    finalTransition.setToX(320);
	    finalTransition.setToY(0);
	    transitions.add(finalTransition);

	    return transitions;
	}

	private void playNextZigZagTransition(List<TranslateTransition> transitions, int currentTransitionIndex) {
	    if (currentTransitionIndex < transitions.size()) {
	        TranslateTransition transition = transitions.get(currentTransitionIndex);
	        transition.setOnFinished(event -> {
	            playNextZigZagTransition(transitions, currentTransitionIndex + 1);
	        });
	        transition.play();
	    }
	}

}

class Dashboard {
	private static Dashboard instance;
	
	private FarmItem rootItem;
	private ImageView drone;
	private Dashboard() {}
	
	public static Dashboard getInstance() {
		if(instance == null) {
			instance = new Dashboard();
		}
		return instance;
	}
	
	public void initializeFarmItemContainer() {
		this.rootItem = new ItemContainer("Root", true);
		FarmItem commandCenterItem = new ItemContainer("Command Center", 300, 0, 130, 130);
		FarmItem droneItem = new Item("Drone", 310, 20, 100, 100);
		
		rootItem.addItem(commandCenterItem);
		commandCenterItem.addItem(droneItem);
		
	}
	
	public TreeItem<FarmItem> getFarmItemsDisplayList(){
		return rootItem.displayInFarmItemContainer();
	}

	public ObservableList<ListViewCommands> getFarmItemCommands(FarmItem item) {
		List<ListViewCommands> listViewItems = new ArrayList<>();;
		if(item instanceof Item) {
			ItemCommands[] ivm = ItemCommands.values();
			for(ItemCommands i : ivm) {
				listViewItems.add(new ListViewCommands(i.toString(),item));
			}
		} else if(item instanceof ItemContainer) {
			if(((ItemContainer) item).getIsRootItem()) {
				listViewItems.add(new ListViewCommands("Add_Item_Container", item));
			} else {
				ItemContainerCommands[] ivm = ItemContainerCommands.values();
				for(ItemContainerCommands i : ivm) {
					listViewItems.add(new ListViewCommands(i.toString(), item));
				}
			}
			
		} 
		
		ObservableList<ListViewCommands> items = FXCollections.observableArrayList(listViewItems);
		return items;
	}
	
	public FarmItem getRootItem() {
		return this.rootItem;
	}
	
	public ImageView getDrone() {
		return this.drone;
	}
	
	public void setDrone(ImageView drone) {
		this.drone = drone;
	}

    // Sets up the entire visualization area, adding all StackPanes and drone ImageView
    public void setVisualization(AnchorPane visualizationArea) {
    	List<StackPane> stackPanes = new ArrayList<>();
	    rootItem.addFarmItemToPane(rootItem, stackPanes);

        // Clear existing children in visualization area and add all StackPanes
        visualizationArea.getChildren().clear();
        visualizationArea.getChildren().addAll(stackPanes);

        // Configure and add the drone image
        drone = new ImageView(new Image("drone.png"));
        drone.setLayoutX(320);
        drone.setLayoutY(40);
        visualizationArea.getChildren().add(drone);
    }
	
}


interface FarmItem {
	
	public void addItem(FarmItem item);
	public boolean removeItem(FarmItem item);
	public TreeItem<FarmItem> displayInFarmItemContainer();
	public void setName(String name);
	public String getName();
	public float getxCoordinate();
	public float getyCoordinate();
	public float getHeight();
	public float getWidth();
	public void setWidth(float width);
	public void setHeight(float height);
	public void setxCoordinate(float parseFloat);
	public void setyCoordinate(float parseFloat);
	public void addFarmItemToPane(FarmItem item, List<StackPane> stackPanes);
	public void setPrice(float newPrice);
	public float getPrice();
}

class Item implements FarmItem{
	private String name;
	private float xCoordinate;
	private float yCoordinate;
	private float height;
	private float width;
	private float price;
	
	public Item() {}
	
	public Item(String name) {
		this.name = name;
	}

	public Item(String name, float xCoordinate, float yCoordinate, float height, float width) {
		super();
		this.name = name;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.height = height;
		this.width = width;
	}

	@Override
	public void addItem(FarmItem item) {
		// TODO Auto-generated method stub
		System.out.println("Not valid");
	}

	@Override
	public boolean removeItem(FarmItem item) {
		// TODO Auto-generated method stub
		System.out.println("Not valid");
		return false;
	}


	@Override
	public TreeItem<FarmItem> displayInFarmItemContainer() {
		// TODO Auto-generated method stub
		return new TreeItem<>(this);
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public float getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	@Override
	public void setPrice(float newPrice) {
		this.price = newPrice;
	}

	@Override
	public float getPrice() {
		return this.price;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void addFarmItemToPane(FarmItem item, List<StackPane> stackPanes) {
		// Not Implemented for Item
	}
}

class ItemContainer implements FarmItem {
	private String name;
	private float xCoordinate;
	private float yCoordinate;
	private float height;
	private float width;
	private float price;
	private List<FarmItem> farmItems;
	private boolean isRootItem;
	
	public ItemContainer() {
		farmItems = new ArrayList<>();
	}
	
	public ItemContainer(String name) {
		this.name = name;
		farmItems = new ArrayList<>();
	}
	
	public ItemContainer(String name, boolean isRootItem) {
		this.name = name;
		farmItems = new ArrayList<>();
		this.isRootItem = isRootItem;
	}
	
	public ItemContainer(String name, float xCoordinate, float yCoordinate, float height, float width) {
		this.name = name;
		farmItems = new ArrayList<>();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.height = height;
		this.width = width;
	}

	@Override
	public void addItem(FarmItem item) {
		farmItems.add(item);
	}
	
	public boolean removeItem(FarmItem target) {
        if (farmItems.remove(target)) {
            return true;
        }
        
        for (FarmItem item : farmItems) {
            if (item instanceof ItemContainer) {
                ItemContainer container = (ItemContainer) item;
                if (container.removeItem(target)) {
                    return true;
                }
            }
        }

        return false;
    }
	
    private StackPane createStackPaneForItem(FarmItem item) {
        StackPane stackPane = new StackPane();
        
        Label label = new Label(item.getName());
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
        StackPane.setAlignment(label, javafx.geometry.Pos.TOP_LEFT);
        
        Rectangle rectangle = new Rectangle(item.getWidth(), item.getHeight());
        rectangle.setFill(null);
        if(item instanceof ItemContainer)
        	rectangle.setStroke(Color.BLACK);
        else
        	rectangle.setStroke(Color.BLUE);
        rectangle.setStrokeWidth(2);
        
        stackPane.setLayoutX(item.getxCoordinate());
        stackPane.setLayoutY(item.getyCoordinate());

        stackPane.getChildren().addAll(label, rectangle);
        return stackPane;
    }

	public void addFarmItemToPane(FarmItem item, List<StackPane> stackPanes) {
	    if (item instanceof ItemContainer) {
	        ItemContainer container = (ItemContainer) item;
	        
	        if (!container.getIsRootItem()) {
	            stackPanes.add(createStackPaneForItem(container));
	        }

	        for (FarmItem nestedItem : container.getFarmItems()) {
	            addFarmItemToPane(nestedItem, stackPanes);
	        }
	    } else {
	        stackPanes.add(createStackPaneForItem(item));
	    }
	}
	
	@Override
	public TreeItem<FarmItem> displayInFarmItemContainer() {
		TreeItem<FarmItem> temp = new TreeItem<FarmItem>(this);
		
		for(FarmItem item : farmItems) {
			temp.getChildren().add(item.displayInFarmItemContainer());
		}
		
		return temp;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}
	
	public boolean getIsRootItem() {
		return isRootItem;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public float getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	@Override
	public void setPrice(float newPrice) {
		this.price = newPrice;
	}

	@Override
	public float getPrice() {
		return this.price;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public List<FarmItem> getFarmItems() {
		return farmItems;
	}

	public void setFarmItems(List<FarmItem> farmItems) {
		this.farmItems = farmItems;
	}
}

enum ItemCommands {
	Add_Item,
	Delete_Item,
	Change_X_Coordinate,
	Change_Y_Coordinate,
	Change_Width,
	Change_Height,
	Change_Name,
	Chnage_Price
}

enum ItemContainerCommands {
	Add_Item_Container,
	Delete_Item_Container,
	Add_Item,
	Change_X_Coordinate,
	Change_Y_Coordinate,
	Change_Width,
	Change_Height,
	Change_Name,
	Change_Price
}

class ListViewCommands {
	private FarmItem item;
	private String commandName;
	
	public ListViewCommands(String name, FarmItem item) {
		commandName = name;
		this.item = item;
	}
	
	public String toString() {
		return commandName;
	}

	public FarmItem getItem() {
		return item;
	}

	public void setItem(FarmItem item) {
		this.item = item;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	
	
}

