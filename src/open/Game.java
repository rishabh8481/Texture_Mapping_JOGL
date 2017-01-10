package open;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

@SuppressWarnings({"serial" })
public class Game extends JFrame implements GLEventListener , KeyListener {

    static double x=0;
	final private int width = 800;
	final private int height = 600;
	List<Double> v_x = new ArrayList<Double>();
	List<Double> v_y = new ArrayList<Double>();
	List<Double> v_z = new ArrayList<Double>();
	List<Double> ux = new ArrayList<Double>();
	List<Double> uy = new ArrayList<Double>();
	List<Double> uz = new ArrayList<Double>();
	List<Double> vx = new ArrayList<Double>();
	List<Double> vy = new ArrayList<Double>();
	List<Double> vz = new ArrayList<Double>();
	List<Double> Nx = new ArrayList<Double>();
	List<Double> Ny = new ArrayList<Double>();
	List<Double> Nz = new ArrayList<Double>();
	List<Double> vt_x = new ArrayList<Double>();
	List<Double> vt_y = new ArrayList<Double>();
	List<Double> vt_z = new ArrayList<Double>();
	List<Double> vn_x = new ArrayList<Double>();
	List<Double> vn_y = new ArrayList<Double>();
	List<Double> vn_z = new ArrayList<Double>();
	List<Integer> f_0 = new ArrayList<Integer>();
	List<Integer> f_1 = new ArrayList<Integer>();
	List<Integer> f_2 = new ArrayList<Integer>();	
	List<Integer> f_3 = new ArrayList<Integer>();
	List<Integer> f_4 = new ArrayList<Integer>();
	List<Integer> f_5 = new ArrayList<Integer>();
	List<Integer> f_6 = new ArrayList<Integer>();
	List<Integer> f_7 = new ArrayList<Integer>();
	List<Integer> f_8 = new ArrayList<Integer>();
	int flag =0;
	public Game() {
		super("Minimal OpenGL");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas(capabilities);
		canvas.addGLEventListener(this);
		this.setName("Minimal OpenGL");
		this.getContentPane().add(canvas);


		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		canvas.requestFocusInWindow();
	}



	@SuppressWarnings("unused")
	public void display( GLAutoDrawable d ) {  
		GLU glu = new GLU();
		GL2 gl = d.getGL().getGL2();
		final GLUT glut     = new GLUT();
		if(flag ==0){

			try {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader("b.txt"));
				String line;
				int linecount=0;
				while((line = br.readLine())!=null){

					if(line.startsWith("vn")){
						String[] s = line.split(" ");
						vn_x.add(Double.valueOf(s[1]));
						vn_y.add(Double.valueOf(s[2]));
						vn_z.add(Double.valueOf(s[3]));
						linecount++;

					}
					if(line.startsWith("vt")){
						String[] s = line.split(" ");
						vt_x.add(Double.valueOf(s[1]));
						vt_y.add(Double.valueOf(s[2]));
				//		vt_z.add(Double.valueOf(s[3]));
						linecount++;
					}
					if(line.startsWith("v ")){
						String[] s = line.split(" ");
						v_x.add(Double.valueOf(s[1])/300);
						v_y.add(Double.valueOf(s[2])/300);
						v_z.add(Double.valueOf(s[3])/300);
						linecount++;
					}
					if(line.startsWith("f")){

						String[] s = line.split(" ");
						// 1 2 3
						String[] str = s[1].split("/");
						//0 1 2
						f_0.add(Integer.parseInt(str[0]));
						f_1.add(Integer.parseInt(str[1]));
						f_2.add(Integer.parseInt(str[2]));

						String[] str1 = s[2].split("/");
						f_3.add(Integer.parseInt(str1[0]));
						f_4.add(Integer.parseInt(str1[1]));
						f_5.add(Integer.parseInt(str1[2]));

						String[] str2 = s[3].split("/");
						f_6.add(Integer.parseInt(str2[0]));
						f_7.add(Integer.parseInt(str2[1]));
						f_8.add(Integer.parseInt(str2[2]));
						linecount++;
					}
				}			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			flag = 1;
		}
        
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();     
		//gl.glPolygonMode(gl.GL_FRONT, gl.GL_LINE);
		//gl.glPolygonMode(gl.GL_BACK, gl.GL_LINE);
		glu.gluLookAt(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        gl.glRotated(60, 0, 0, 0);
		gl.glTranslated(0, 0, 7);
        //glut.glutSolidTeapot(1.5);
			for(int i=0;i<f_1.size();i++)
			{
			 
				/*ux.add((v_x.get((f_0.get(i))-1))-(v_x.get((f_3.get(i))-1)));
				uy.add((v_y.get((f_0.get(i))-1))-(v_y.get((f_3.get(i))-1)));
				uz.add((v_z.get((f_0.get(i))-1))-(v_z.get((f_3.get(i))-1)));
				vx.add((v_x.get((f_6.get(i))-1))-(v_x.get((f_0.get(i))-1)));
				vy.add((v_y.get((f_6.get(i))-1))-(v_y.get((f_0.get(i))-1)));
				vz.add((v_z.get((f_6.get(i))-1))-(v_z.get((f_0.get(i))-1)));*/

				ux.add((v_x.get((f_3.get(i))-1))-(v_x.get((f_0.get(i))-1)));
				uy.add((v_y.get((f_3.get(i))-1))-(v_y.get((f_0.get(i))-1)));
				uz.add((v_z.get((f_3.get(i))-1))-(v_z.get((f_0.get(i))-1)));
				vx.add((v_x.get((f_6.get(i))-1))-(v_x.get((f_3.get(i))-1)));
				vy.add((v_y.get((f_6.get(i))-1))-(v_y.get((f_3.get(i))-1)));
				vz.add((v_z.get((f_6.get(i))-1))-(v_z.get((f_3.get(i))-1)));
				Nx.add((uy.get(i)*vz.get(i))-(uz.get(i)*vy.get(i)));
				Ny.add((uz.get(i)*vx.get(i))-(ux.get(i)*vz.get(i)));
				Nz.add((ux.get(i)*vy.get(i))-(uy.get(i)*vx.get(i)));
			}
			for(int i = 0;i< f_1.size();i++){
				double mm = ((Nx.get(i) * Nx.get(i)) + (Ny.get(i) * Ny.get(i)) + (Nz.get(i) * Nz.get(i)));
				Nx.set(i, (Nx.get(i)/Math.sqrt(mm)));
				Ny.set(i, (Ny.get(i)/Math.sqrt(mm)));
				Nz.set(i, (Nz.get(i)/Math.sqrt(mm)));
			}
		// Your code begins here 
		gl.glBegin(GL.GL_TRIANGLES);

		for(int i=0;i<f_1.size();i++)
		{
			gl.glNormal3d(Nx.get((f_2.get(i))-1), Ny.get((f_2.get(i))-1), Nz.get((f_2.get(i))-1));
			//gl.glTexCoord2d(vt_x.get((f_1.get(i))-1), vt_y.get((f_2.get(i))-1));
			gl.glVertex3d(v_x.get((f_0.get(i))-1), v_y.get((f_0.get(i))-1), v_z.get((f_0.get(i))-1));


			gl.glNormal3d(Nx.get((f_5.get(i))-1), Ny.get((f_5.get(i))-1), Nz.get((f_5.get(i))-1));
			//gl.glTexCoord2d(vt_x.get((f_4.get(i))-1), vt_y.get((f_4.get(i))-1));
			gl.glVertex3d(v_x.get((f_3.get(i))-1), v_y.get((f_3.get(i))-1), v_z.get((f_3.get(i))-1));

			gl.glNormal3d(Nx.get((f_8.get(i))-1), Ny.get((f_8.get(i))-1), Nz.get((f_8.get(i))-1));
			//gl.glTexCoord2d(vt_x.get((f_7.get(i))-1), vt_y.get((f_7.get(i))-1));
			gl.glVertex3d(v_x.get((f_6.get(i))-1), v_y.get((f_6.get(i))-1), v_z.get((f_6.get(i))-1));

		}
		gl.glFlush();

		gl.glEnd();    
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.392f, 0.584f, 0.929f, 1.0f);
		GLU glu = new GLU();
		//GL gl = drawable.getGL();
		
		gl.glClearColor( .2f,.2f,.5f,0 );
		
		((GLMatrixFunc) gl).glMatrixMode(GL2.GL_PROJECTION);
		((GLMatrixFunc) gl).glLoadIdentity();
		glu.gluPerspective(20.0f, 1.0f, 1.0f, 200.0f);
		((GLMatrixFunc) gl).glMatrixMode(GL2.GL_MODELVIEW);
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(gl.GL_CULL_FACE);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_LIGHTING); 
		gl.glEnable(GL2.GL_LIGHT0);  
		gl.glEnable(GL2.GL_NORMALIZE); 
		float[] ambientLight = { 0.0f, 0.0f, 0.0f,0f };  // weak RED ambient 
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0); 

		float[] diffuseLight = { 1f,1f,1f,0f };  // multicolor diffuse 
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0); 
		 
	}

	@Override
	public void reshape(GLAutoDrawable d, int arg1, int arg2, int arg3, int arg4) {
		GL2 gl = d.getGL().getGL2();
		GLU glu = new GLU();
		gl.glViewport(0,0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(60.,(double)width/height, 1.0, 300.);
		gl.glMatrixMode(GL2.GL_MODELVIEW); 
	}

	public void play() {
	}



	@Override
	public void keyPressed(KeyEvent key) {
	
		switch (key.getKeyChar()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_W:
			x= x+5.0;
			System.out.println("hello");
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent key) {
	}

}
