public class Main
{
    public static void main(String[] args)
    {
        Display display = new Display(800, 600, "3D Software Rendering");
        RenderContext target = display.GetFrameBuffer();
        Vertex minYVert = new Vertex(-1, -1, 0);
        Vertex midYVert = new Vertex(0, 1, 0);
        Vertex maxYVert = new Vertex(1, -1, 0);
        Matrix4f projection = new Matrix4f().InitPerspective((float)Math.toRadians(70.0f),(float)target.GetWidth()/(float)target.GetHeight(),0.1f,1000.0f);
        float rotCounter = 0.0f;
        long previousTime = System.nanoTime();
        while(true)
        {
            long currentTime = System.nanoTime();
            float delta = (float)((currentTime - previousTime)/1000000000.0);
            previousTime = currentTime;
            rotCounter += delta;
            Matrix4f translation = new Matrix4f().InitTranslation(0.0f, 0.0f, 3.0f);
            Matrix4f rotation = new Matrix4f().InitRotation(0.0f, rotCounter, 0.0f);
            Matrix4f transform = projection.Mul(translation.Mul(rotation));
            target.Clear((byte)0x00);
            target.FillTriangle(maxYVert.Transform(transform),midYVert.Transform(transform),minYVert.Transform(transform));
            display.SwapBuffers();
        }
    }
}