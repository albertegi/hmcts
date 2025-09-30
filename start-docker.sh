#!/bin/bash

# HMCTS Docker Setup Script

echo "🐳 Starting HMCTS Task Management System with Docker..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker Desktop first."
    exit 1
fi

# Check if docker-compose is available
if ! command -v docker-compose &> /dev/null; then
    echo "❌ docker-compose is not installed. Please install Docker Compose."
    exit 1
fi

echo "📦 Building and starting containers..."

# Build and start the services
docker-compose up --build -d

echo "⏳ Waiting for services to start..."

# Wait a bit for services to start
sleep 10

echo "✅ Services should be ready!"

echo ""
echo "🎉 HMCTS Task Management System is now running!"
echo ""
echo "📱 Web Interface: http://localhost:8080/tasks"
echo "📚 API Documentation: http://localhost:8080/swagger-ui.html"
echo ""
echo "🛑 To stop the application, run: docker-compose down"
echo "📊 To view logs, run: docker-compose logs -f"
echo ""
